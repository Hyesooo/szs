package com.work.szs.refund.domain;

import com.work.szs.common.entity.BaseEntity;
import com.work.szs.refund.domain.enums.DeductType;
import com.work.szs.scrap.application.dto.result.DeductResult;
import com.work.szs.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "deducts")
@NoArgsConstructor
@Getter
public class Deduct extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "tax_year", nullable = false)
    private Integer year;

    @Column(name = "tax_month", nullable = false)
    private Integer month;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", updatable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "deduct_type", updatable = false)
    private DeductType type;

    private Deduct(int year, int month, long amount, User user, DeductType type) {
        this.year = year;
        this.month = month;
        this.amount = amount;
        this.user = user;
        this.type = type;
    }

    public static Deduct toEntity(DeductResult command, User user) {
        return new Deduct(command.getYear(), command.getMonth(), command.getAmount(), user, command.getType());
    }

}
