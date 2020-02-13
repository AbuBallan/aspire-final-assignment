package com.ab.inventory.model;

import lombok.Data;

import java.util.List;

@Data
public class Item {

    private String itemNo;

    private String name;

    private String description;

    private int manId;

    private int qty;

    private int price;

    private List<ImageLink> images;

}
