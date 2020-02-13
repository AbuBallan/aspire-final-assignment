package com.aspire.iteminventory.adapter.repository.rds;

import com.aspire.iteminventory.adapter.repository.rds.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ItemJpaRepository extends JpaRepository<ItemEntity, Long> {
}