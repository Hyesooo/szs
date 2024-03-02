package com.work.szs.refund.domain.enums;

public enum IncomeDeductType {
    CREDIT_CARD,
    NATIONAL_PENSION;

    @Override
    public String toString() {
        return name();
    }
}
