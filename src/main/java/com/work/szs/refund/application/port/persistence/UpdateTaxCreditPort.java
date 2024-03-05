package com.work.szs.refund.application.port.persistence;

import com.work.szs.refund.domain.TaxCredit;

public interface UpdateTaxCreditPort {
    public TaxCredit saveTaxCredit(TaxCredit taxCredit);
}
