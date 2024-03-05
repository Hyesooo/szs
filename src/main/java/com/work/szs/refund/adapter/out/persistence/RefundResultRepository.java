package com.work.szs.refund.adapter.out.persistence;


import com.work.szs.scrap.domain.RefundResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefundResultRepository extends JpaRepository<RefundResult, Long> {
}