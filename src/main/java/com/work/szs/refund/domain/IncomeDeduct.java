package com.work.szs.refund.domain;

import com.work.szs.common.entity.BaseEntity;
import com.work.szs.refund.domain.enums.IncomeDeductType;
import com.work.szs.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "income_deducts")
@NoArgsConstructor
@Getter
public class IncomeDeduct extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "month", nullable = false)
    private Integer month;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", updatable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private IncomeDeductType type;

    private IncomeDeduct(int year, int month, long amount, User user, IncomeDeductType type) {
        this.year = year;
        this.month = month;
        this.amount = amount;
        this.user = user;
        this.type = type;
    }

    public static IncomeDeduct of(int year, int month, long amount, User user, IncomeDeductType type) {
        return new IncomeDeduct(year, month, amount, user, type);
    }

}
