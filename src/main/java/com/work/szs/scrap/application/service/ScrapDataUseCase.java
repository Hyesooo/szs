package com.work.szs.scrap.application.service;

import com.work.szs.common.config.TokenService;
import com.work.szs.common.dto.TokenDto;
import com.work.szs.common.exception.BusinessInvalidValueException;
import com.work.szs.refund.application.port.persistence.UpdateDeductPort;
import com.work.szs.refund.application.port.persistence.UpdateRefundResultPort;
import com.work.szs.refund.application.port.persistence.UpdateTaxCreditPort;
import com.work.szs.refund.domain.Deduct;
import com.work.szs.refund.domain.RefundResult;
import com.work.szs.refund.domain.TaxCredit;
import com.work.szs.scrap.application.dto.command.DeductCommand;
import com.work.szs.scrap.application.dto.command.ScrapDataCommand;
import com.work.szs.scrap.application.dto.request.ScrapDataRequest;
import com.work.szs.scrap.application.port.client.ScrapDataPort;
import com.work.szs.user.application.port.out.LoadUserPort;
import com.work.szs.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScrapDataUseCase {
    private final TokenService tokenService;

    private final ScrapDataPort scrapDataPort;
    private final UpdateDeductPort updateDeductPort;
    private final UpdateTaxCreditPort updateTaxCreditPort;
    private final LoadUserPort loadUserPort;
    private final UpdateRefundResultPort updateRefundResultPort;

    @Transactional
    public void inputBaseData() {
        TokenDto tokenDto = tokenService.getCurrentTokenInfo();

        User user = loadUserPort.loadUserByUserId(tokenDto.getUserId())
                .orElseThrow(() -> new BusinessInvalidValueException("사용자 정보를 확인해주세요"));

        ScrapDataCommand command = scrapDataPort.getScrapData(new ScrapDataRequest(user.getName(),
                user.getRegNo().replaceAll("(\\d{6})(\\d{7})", "$1-$2")));

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
