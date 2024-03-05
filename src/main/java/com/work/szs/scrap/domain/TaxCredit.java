package com.work.szs.scrap.domain;

import com.work.szs.common.entity.BaseEntity;
import com.work.szs.scrap.application.dto.command.TaxCreditCommand;
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

    @Column(name = "tax_year", nullable = false)
    private Integer year;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", updatable = false)
    private User user;

    private TaxCredit(int year, long amount, User user) {
        this.amount = amount;
        this.year = year;
        this.user = user;
    }

    public static TaxCredit toEntity(TaxCreditCommand command, User user) {
        return new TaxCredit(command.getYear(), command.getAmount(), user);
    }

}
