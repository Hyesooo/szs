package com.work.szs.scrap.domain.enums;

public enum DeductType {
    CREDIT_CARD,
    NATIONAL_PENSION,
    TAX_CREDIT;

    @Override
    public String toString() {
        return name();
    }
}
