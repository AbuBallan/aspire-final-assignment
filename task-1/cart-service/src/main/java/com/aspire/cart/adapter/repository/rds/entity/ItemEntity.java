package com.aspire.cart.adapter.repository.rds.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "Item")
public class ItemEntity {

    @Id
    private Long itemNo;

    private int price;

    private int qty;

}