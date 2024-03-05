package com.work.szs.refund.adapter.out.persistence;

import com.work.szs.common.annotation.PersistenceAdapter;
import com.work.szs.refund.application.port.persistence.LoadTaxCreditPort;
import com.work.szs.refund.application.port.persistence.UpdateTaxCreditPort;
import com.work.szs.scrap.domain.TaxCredit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@PersistenceAdapter
@Component
@RequiredArgsConstructor
public class TaxCreditPersistenceAdapter implements LoadTaxCreditPort, UpdateTaxCreditPort {

    private final TaxCreditRepository taxCreditRepository;

//    @Override
//    public Optional<TaxCredit> loadYearTaxCreditByUser(long userId, int year) {
//        return taxCreditRepository;
//    }

    @Override
    public TaxCredit saveTaxCredit(TaxCredit taxCredit) {
        return taxCreditRepository.save(taxCredit);
    }
}