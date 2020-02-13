package com.ab.cart.adapter.repository.item.rds.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "Item")
public class ItemEntity {

    @Id
    private String itemNo;

    private int price;

}
