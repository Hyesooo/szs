package com.work.szs.scrap.application.dto.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TaxCreditResult {
    private int year;
    private long amount;
}
