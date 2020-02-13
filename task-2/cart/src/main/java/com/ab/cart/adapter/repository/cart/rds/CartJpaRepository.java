package com.ab.cart.adapter.repository.cart.rds;

import com.ab.cart.adapter.repository.cart.rds.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartJpaRepository extends JpaRepository<CartEntity, String> {
}
