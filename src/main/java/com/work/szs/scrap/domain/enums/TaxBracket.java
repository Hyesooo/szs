package com.work.szs.scrap.domain.enums;

public enum TaxBracket {
    BRACKET1(0, 10_400_000, 0.06, 0),
    BRACKET2(10_400_000, 50_000_000, 0.15, 840_000),
    BRACKET3(50_000_000, 88_000_000, 0.24, 6_260_000),
    BRACKET4(88_000_000, 150_000_000, 0.35, 15_360_000),
    BRACKET5(150_000_000, 300_000_000, 0.38, 37_060_000),
    BRACKET6(300_000_000, 500_000_000, 0.40, 94_060_000),
    BRACKET7(500_000_000, 1_000_000_000, 0.42, 174_060_000),
    BRACKET8(1_000_000_000, Long.MAX_VALUE, 0.45, 384_060_000);

    private final long lowerBound;
    private final long upperBound;
    private final double taxRate;
    private final long taxBase;

    TaxBracket(long lowerBound, long upperBound, double taxRate, long taxBase) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.taxRate = taxRate;
        this.taxBase = taxBase;
    }

    public static long calculateTax(long taxableIncome) {
        double tax = 0.0;
        for (TaxBracket bracket : TaxBracket.values()) {
            if (taxableIncome <= bracket.upperBound) {
                long incomeOverThreshold = taxableIncome - bracket.lowerBound;
                tax = (incomeOverThreshold * bracket.taxRate) + bracket.taxBase;
                break;
            }
        }
        return Math.round(tax);
    }
}
