package com.work.szs.scrap.adapter.out.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.work.szs.scrap.adapter.out.client.config.DeductDeserializer;
import com.work.szs.scrap.application.dto.command.ScrapDataCommand;
import com.work.szs.scrap.domain.Deduct;
import com.work.szs.scrap.domain.TaxCredit;
import com.work.szs.scrap.domain.enums.DeductType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ScrapResult {
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

    public ScrapDataCommand convertToCommand() {
        List<Deduct> deductList = this.deductions.stream()
                .filter(deduction -> deduction.type != DeductType.TAX_CREDIT)
                .map(deduction -> Deduct.withoutUser(deduction.year, deduction.month, deduction.amount, deduction.type))
                .collect(Collectors.toList());

        TaxCredit taxCredit = null;
        Deduction taxCreditDeduction = this.deductions.stream()
                .filter(deduction -> deduction.type == DeductType.TAX_CREDIT)
                .findFirst()
                .orElse(null);

        if (taxCreditDeduction != null) {
            taxCredit = TaxCredit.withoutUser((long) taxCreditDeduction.amount, taxCreditDeduction.year);
        }

        return new ScrapDataCommand(this.name, this.totalIncomeAmount, deductList, taxCredit);
    }
}
