package com.work.szs.refund.adapter.out.persistence;

import com.work.szs.common.annotation.PersistenceAdapter;
import com.work.szs.refund.application.port.persistence.UpdateTaxCreditPort;
import com.work.szs.scrap.domain.TaxCredit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@PersistenceAdapter
@Component
@RequiredArgsConstructor
public class TaxCreditPersistenceAdapter implements UpdateTaxCreditPort {

    private final TaxCreditRepository taxCreditRepository;

    @Override
    public TaxCredit saveTaxCredit(TaxCredit taxCredit) {
        return taxCreditRepository.save(taxCredit);
    }
}