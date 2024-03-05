package com.work.szs.scrap.domain.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RefundStatus {
    PREPARE("초기상태"),
    SCRAP("기초정보스크랩"),
    COMPLETE("계산완료");

    private final String description;

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name();
    }
}
