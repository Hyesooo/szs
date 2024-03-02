package com.work.szs.refund.domain;

import com.work.szs.common.entity.BaseEntity;
import com.work.szs.refund.domain.enums.RefundStatus;
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

    @Column(name = "totalIncomeAmount", nullable = false)
    private Long totalIncomeAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", updatable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private RefundStatus status;

    private RefundResult(User user) {
        this.user = user;
        this.status = RefundStatus.PREPARE;
    }

    public static RefundResult of(User user) {
        return new RefundResult(user);
    }

}