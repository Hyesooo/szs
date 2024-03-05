package com.work.szs.refund.domain;

import com.work.szs.common.entity.BaseEntity;
import com.work.szs.common.exception.BusinessInvalidValueException;
import com.work.szs.refund.domain.enums.RefundStatus;
import com.work.szs.refund.domain.enums.TaxBracket;
import com.work.szs.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "refund_results")
@NoArgsConstructor
@Getter
public class RefundResult extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "tax_year", nullable = false)
    private Integer year;

    @Column(name = "total_income_amount", nullable = false)
    private Long totalIncomeAmount;

    @Column(name = "total_deduct_amount", nullable = false)
    private Long totalDeductAmount;

    @Column(name = "total_tax_credit_amount", nullable = false)
    private Long totalTaxCreditAmount;

    @Column(name = "tax liability")
    private Long taxLiability;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", updatable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private RefundStatus status = RefundStatus.PREPARE;

    private RefundResult(User user, int year, long totalIncomeAmount, long totalDeductAmount, long totalTaxCreditAmount, RefundStatus status) {
        this.user = user;
        this.year = year;
        this.totalIncomeAmount = totalIncomeAmount;
        this.totalDeductAmount = totalDeductAmount;
        this.totalTaxCreditAmount = totalTaxCreditAmount;
        this.status = status;
    }

    public static RefundResult of(User user, int year, long totalIncomeAmount, long totalDeductAmount, long totalTaxCreditAmount) {
        return new RefundResult(user, year, totalIncomeAmount, totalDeductAmount, totalTaxCreditAmount, RefundStatus.SCRAP);
    }

    public long calc() {
        if (this.status == RefundStatus.PREPARE) {
            throw new BusinessInvalidValueException("기초정보가 없습니다.");
        }

        this.taxLiability = TaxBracket.calculateTax(this.totalIncomeAmount - this.totalDeductAmount) - this.totalTaxCreditAmount;
        this.status = RefundStatus.COMPLETE;

        return this.taxLiability;
    }
}
