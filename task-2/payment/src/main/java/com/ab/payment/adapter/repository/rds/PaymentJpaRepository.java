package com.ab.payment.adapter.repository.rds;

import com.ab.payment.adapter.repository.rds.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, String> {
}
