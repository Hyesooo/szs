package com.work.szs.scrap.application.dto.command;

import com.work.szs.scrap.domain.Deduct;
import com.work.szs.scrap.domain.TaxCredit;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ScrapDataCommand {
    private String name;
    private Long totalIncomeAmount;
    private List<Deduct> deductList;
    private TaxCredit taxCredit;
}
