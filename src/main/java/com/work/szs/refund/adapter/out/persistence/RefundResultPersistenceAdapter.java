package com.work.szs.refund.adapter.out.persistence;

import com.work.szs.common.annotation.PersistenceAdapter;
import com.work.szs.refund.application.port.persistence.UpdateRefundResultPort;
import com.work.szs.scrap.domain.RefundResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@PersistenceAdapter
@Component
@RequiredArgsConstructor
public class RefundResultPersistenceAdapter implements UpdateRefundResultPort {

    private final RefundResultRepository refundResultRepository;

    @Override
    public RefundResult saveResult(RefundResult r) {
        return refundResultRepository.save(r);
    }
}