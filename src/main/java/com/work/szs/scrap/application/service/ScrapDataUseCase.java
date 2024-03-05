package com.work.szs.scrap.application.service;

import com.work.szs.common.exception.BusinessInvalidValueException;
import com.work.szs.refund.application.port.persistence.UpdateDeductPort;
import com.work.szs.refund.application.port.persistence.UpdateRefundResultPort;
import com.work.szs.refund.application.port.persistence.UpdateTaxCreditPort;
import com.work.szs.scrap.application.dto.command.DeductCommand;
import com.work.szs.scrap.application.dto.command.ScrapDataCommand;
import com.work.szs.scrap.application.dto.request.ScrapDataRequest;
import com.work.szs.scrap.application.port.client.ScrapDataPort;
import com.work.szs.refund.domain.Deduct;
import com.work.szs.refund.domain.RefundResult;
import com.work.szs.refund.domain.TaxCredit;
import com.work.szs.user.application.port.out.LoadUserPort;
import com.work.szs.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScrapDataUseCase {
    private final TextEncryptor textEncryptor;

    private final ScrapDataPort scrapDataPort;
    private final UpdateDeductPort updateDeductPort;
    private final UpdateTaxCreditPort updateTaxCreditPort;
    private final LoadUserPort loadUserPort;
    private final UpdateRefundResultPort updateRefundResultPort;

    @Transactional
    public void inputBaseData(ScrapDataRequest request) {
        User user = loadUserPort.loadUserByName(request.getName())
                .stream().filter(u -> u.getRegNo().equals(request.getRegNo().replace("-", "")))
                .findAny()
                .orElseThrow(() -> new BusinessInvalidValueException("이름과 주민번호를 확인해주세요."));

        ScrapDataCommand command = scrapDataPort.getScrapData(request);

        // 소득공제
        command.getDeductList().forEach(deduct -> updateDeductPort.saveDeduct(Deduct.toEntity(deduct, user)));
        long totalDeductAmount = command.getDeductList().stream().mapToLong(DeductCommand::getAmount).sum();

        // 세액공제
        updateTaxCreditPort.saveTaxCredit(TaxCredit.toEntity(command.getTaxCredit(), user));

        // 결과
        RefundResult result = RefundResult.of(user, command.getYear(), command.getTotalIncomeAmount(), totalDeductAmount, command.getTaxCredit().getAmount());
        updateRefundResultPort.saveResult(result);
    }

}
