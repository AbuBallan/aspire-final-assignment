package com.ab.inventory.adapter.repository.impl.rds.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class ItemEntity {

    @Id
    private String itemNo;

    private String name;

    private String description;

    private int manId;

    private int qty;

    private int price;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "item_no")
    private List<ImageLinkEntity> images;

}
