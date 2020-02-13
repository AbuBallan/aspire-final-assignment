package com.aspire.cart.adapter.repository.rds;

import com.aspire.cart.adapter.repository.rds.entity.CartAggregateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartAggregateJpaRepository extends JpaRepository<CartAggregateEntity, Long> {
}
