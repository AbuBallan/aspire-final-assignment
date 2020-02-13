package com.ab.inventory.adapter.repository.impl.rds;

import com.ab.inventory.adapter.repository.impl.rds.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ItemJpaRepository extends JpaRepository<ItemEntity, String> {
}
