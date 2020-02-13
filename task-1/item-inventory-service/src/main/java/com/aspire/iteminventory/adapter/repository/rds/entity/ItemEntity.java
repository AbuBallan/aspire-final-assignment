package com.aspire.iteminventory.adapter.repository.rds.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Item")
@Data
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemNo;

    private String name;

    private String description;

    private int manId;

    private int qty;

    private int price;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "item_no")
    private List<ImageLinkEntity> images;

}