package com.work.szs.scrap.domain;

import com.work.szs.common.entity.BaseEntity;
import com.work.szs.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tax_credits")
@NoArgsConstructor
@Getter
public class TaxCredit extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", updatable = false)
    private User user;

    private TaxCredit(long amount, int year, User user) {
        this.amount = amount;
        this.year = year;
        this.user = user;
    }

    private TaxCredit(long amount, int year) {
        this(amount, year, null);
    }

    public static TaxCredit of(long amount, int year, User user) {
        return new TaxCredit(amount, year, user);
    }

    public static TaxCredit withoutUser(long amount, int year) {
        return new TaxCredit(amount, year);
    }

}
