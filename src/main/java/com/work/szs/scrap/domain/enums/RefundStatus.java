package com.work.szs.scrap.domain.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RefundStatus {
    PREPARE("초기상태"),
    SCRAP("기초정보스크랩"),
    COMPLETE_TAX_BASE("과세표준계산"),
    COMPLETE_TAX_AMOUNT("산출세액계산"),
    COMPLETE_DETERMINED_TAX("결정세액 계산");

    private final String description;

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name();
    }
}
