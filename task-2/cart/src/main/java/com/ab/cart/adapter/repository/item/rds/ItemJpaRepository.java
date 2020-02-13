package com.ab.cart.adapter.repository.item.rds;

import com.ab.cart.adapter.repository.item.rds.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ItemJpaRepository extends JpaRepository<ItemEntity, String> {
}
