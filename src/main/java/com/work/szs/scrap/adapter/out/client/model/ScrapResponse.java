package com.work.szs.scrap.adapter.out.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.work.szs.scrap.adapter.out.client.config.DeductDeserializer;
import com.work.szs.scrap.application.dto.result.DeductResult;
import com.work.szs.scrap.application.dto.result.ScrapDataResult;
import com.work.szs.scrap.application.dto.result.TaxCreditResult;
import com.work.szs.refund.domain.enums.DeductType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ScrapResponse {
    @JsonProperty("이름")
    private String name;

    @JsonProperty("종합소득금액")
    private long totalIncomeAmount;

    @JsonProperty("소득공제")
    @JsonDeserialize(using = DeductDeserializer.class)
    private List<Deduction> deductions;

    @Getter
    @AllArgsConstructor
    public static class Deduction {
        private int year;
        private int month;
        private DeductType type;
        private double amount;

        public Deduction(int year, DeductType type, double amount) {
            this.year = year;
            this.amount = amount;
            this.type = type;
        }
    }

    public ScrapDataResult convertToCommand() {
        List<DeductResult> deductList = this.deductions.stream()
                .filter(deduction -> deduction.type != DeductType.TAX_CREDIT)
                .map(deduction -> new DeductResult(deduction.year, deduction.month, Math.round(deduction.amount), deduction.type))
                .collect(Collectors.toList());

        int year = deductList != null && !deductList.isEmpty() ? deductList.get(0).getYear() : 0;

        TaxCreditResult taxCredit = null;
        Deduction taxCreditDeduction = this.deductions.stream()
                .filter(deduction -> deduction.type == DeductType.TAX_CREDIT)
                .findFirst()
                .orElse(null);

        if (taxCreditDeduction != null) {
            taxCredit = new TaxCreditResult(taxCreditDeduction.year, (long) taxCreditDeduction.amount);
            if (year == 0) year = taxCredit.getYear();
        }

        return new ScrapDataResult(this.name, year, this.totalIncomeAmount, deductList, taxCredit);
    }
}
