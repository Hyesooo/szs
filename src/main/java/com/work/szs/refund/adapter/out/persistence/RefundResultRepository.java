package com.work.szs.refund.adapter.out.persistence;


import com.work.szs.refund.domain.RefundResult;
import com.work.szs.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefundResultRepository extends JpaRepository<RefundResult, Long> {
    Optional<RefundResult> findRefundResultByUser(User user);
}