package com.work.szs.refund.adapter.out.persistence;

import com.work.szs.common.annotation.PersistenceAdapter;
import com.work.szs.refund.application.port.persistence.UpdateDeductPort;
import com.work.szs.scrap.domain.Deduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@PersistenceAdapter
@Component
@RequiredArgsConstructor
public class DeductPersistenceAdapter implements UpdateDeductPort {

    private final DeductRepository deductRepository;

    @Override
    public Deduct saveDeduct(Deduct deduct) {
        return deductRepository.save(deduct);
    }
}