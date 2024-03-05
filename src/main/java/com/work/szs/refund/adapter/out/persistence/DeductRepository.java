package com.work.szs.refund.adapter.out.persistence;


import com.work.szs.refund.domain.Deduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeductRepository extends JpaRepository<Deduct, Long> {
}