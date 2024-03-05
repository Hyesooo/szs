package com.work.szs.scrap.domain;

import com.work.szs.common.entity.BaseEntity;
import com.work.szs.scrap.domain.enums.RefundStatus;
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

    @Column(name = "totalIncomeAmount", nullable = false)
    private Long totalIncomeAmount;

    @Column(name = "totalDeductAmount", nullable = false)
    private Long totalDeductAmount;

    @Column(name = "totalTaxCreditAmount", nullable = false)
    private Long totalTaxCreditAmount;

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
}
