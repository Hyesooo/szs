package com.work.szs.scrap.application.dto.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ScrapDataCommand {
    private String name;
    private Integer year;
    private Long totalIncomeAmount;
    private List<DeductCommand> deductList;
    private TaxCreditCommand taxCredit;
}
