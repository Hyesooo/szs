package com.work.szs.refund.domain.enums;

import java.math.BigDecimal;
import java.math.RoundingMode;

public enum TaxBracket {
    BRACKET1(new BigDecimal("0"), new BigDecimal("1400000"), new BigDecimal("0.06"), BigDecimal.ZERO),
    BRACKET2(new BigDecimal("1400000"), new BigDecimal("5000000"), new BigDecimal("0.15"), new BigDecimal("108000")),
    BRACKET3(new BigDecimal("5000000"), new BigDecimal("8800000"), new BigDecimal("0.24"), new BigDecimal("522000")),
    BRACKET4(new BigDecimal("8800000"), new BigDecimal("15000000"), new BigDecimal("0.35"), new BigDecimal("1496000")),
    BRACKET5(new BigDecimal("15000000"), new BigDecimal("30000000"), new BigDecimal("0.38"), new BigDecimal("1940000")),
    BRACKET6(new BigDecimal("30000000"), new BigDecimal("50000000"), new BigDecimal("0.40"), new BigDecimal("2546000")),
    BRACKET7(new BigDecimal("50000000"), new BigDecimal("100000000"), new BigDecimal("0.42"), new BigDecimal("3546000")),
    BRACKET8(new BigDecimal("100000000"), new BigDecimal(Integer.MAX_VALUE), new BigDecimal("0.45"), new BigDecimal("6546000"));

    private final BigDecimal lowerBound;
    private final BigDecimal upperBound;
    private final BigDecimal taxRate;
    private final BigDecimal deduction;

    TaxBracket(BigDecimal lowerBound, BigDecimal upperBound, BigDecimal taxRate, BigDecimal deduction) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.taxRate = taxRate;
        this.deduction = deduction;
    }

    public static BigDecimal calculateTax(BigDecimal taxableIncome) {
        BigDecimal tax = BigDecimal.ZERO;
        for (TaxBracket bracket : TaxBracket.values()) {
            if (taxableIncome.compareTo(bracket.upperBound) <= 0) {
                BigDecimal incomeOverThreshold = taxableIncome.subtract(bracket.lowerBound);
                BigDecimal taxForBracket = incomeOverThreshold.multiply(bracket.taxRate).subtract(bracket.deduction);
                tax = tax.add(taxForBracket);
                break;
            }
        }
        return tax.setScale(0, RoundingMode.HALF_UP);
    }

}
