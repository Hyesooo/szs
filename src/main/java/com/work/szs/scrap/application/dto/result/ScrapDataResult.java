package com.work.szs.scrap.application.dto.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ScrapDataResult {
    private String name;
    private Integer year;
    private Long totalIncomeAmount;
    private List<DeductResult> deductList;
    private TaxCreditResult taxCredit;
}
