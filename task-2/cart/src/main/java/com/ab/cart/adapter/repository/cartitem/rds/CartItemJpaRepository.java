package com.ab.cart.adapter.repository.cartitem.rds;

import com.ab.cart.adapter.repository.cartitem.rds.entity.CartItemEntity;
import com.ab.cart.adapter.repository.cartitem.rds.entity.CartItemKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemJpaRepository extends JpaRepository<CartItemEntity, CartItemKey> {
}
