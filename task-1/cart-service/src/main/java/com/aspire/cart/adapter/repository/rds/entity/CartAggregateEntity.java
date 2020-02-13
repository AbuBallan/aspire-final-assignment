package com.aspire.cart.adapter.repository.rds.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "CartAggregate")
public class CartAggregateEntity {

    @Id
    @GeneratedValue
    private Long cartId;

    private String userId;

    private String cartStatus;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id")
    private List<ItemEntity> items;
}