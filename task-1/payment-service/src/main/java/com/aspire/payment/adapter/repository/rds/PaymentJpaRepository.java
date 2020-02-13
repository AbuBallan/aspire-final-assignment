package com.aspire.payment.adapter.repository.rds;

import com.aspire.payment.adapter.repository.rds.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, Long> {
}
