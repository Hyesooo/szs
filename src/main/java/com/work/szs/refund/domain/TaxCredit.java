package com.work.szs.refund.domain;

import com.work.szs.common.entity.BaseEntity;
import com.work.szs.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tax_credit")
@NoArgsConstructor
@Getter
public class TaxCredit extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", updatable = false)
    private User user;

    private TaxCredit(long amount, User user) {
        this.amount = amount;
        this.user = user;
    }

    public static TaxCredit of(long amount, User user) {
        return new TaxCredit(amount, user);
    }

}
