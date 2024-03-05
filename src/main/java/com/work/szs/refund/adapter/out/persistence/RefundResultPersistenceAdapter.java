package com.work.szs.refund.adapter.out.persistence;

import com.work.szs.common.annotation.PersistenceAdapter;
import com.work.szs.refund.application.port.persistence.LoadRefundResultPort;
import com.work.szs.refund.application.port.persistence.UpdateRefundResultPort;
import com.work.szs.scrap.domain.RefundResult;
import com.work.szs.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@PersistenceAdapter
@Component
@RequiredArgsConstructor
public class RefundResultPersistenceAdapter implements UpdateRefundResultPort, LoadRefundResultPort {

    private final RefundResultRepository refundResultRepository;

    @Override
    public RefundResult saveResult(RefundResult r) {
        return refundResultRepository.save(r);
    }

    @Override
    public Optional<RefundResult> loadResultByUser(User user) {
        return refundResultRepository.findRefundResultByUser(user);
    }
}