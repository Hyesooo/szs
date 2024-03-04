package com.work.szs.scrap.domain;

import com.work.szs.common.entity.BaseEntity;
import com.work.szs.scrap.domain.enums.DeductType;
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

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "month", nullable = false)
    private Integer month;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", updatable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private DeductType type;

    private Deduct(int year, int month, double amount, User user, DeductType type) {
        this.year = year;
        this.month = month;
        this.amount = amount;
        this.user = user;
        this.type = type;
    }

    private Deduct(int year, int month, double amount, DeductType type) {
        this(year, month, amount, null, type);
    }

    public static Deduct of(int year, int month, double amount, User user, DeductType type) {
        return new Deduct(year, month, amount, user, type);
    }

    public static Deduct withoutUser(int year, int month, double amount, DeductType type) {
        return new Deduct(year, month, amount, type);
    }

}
