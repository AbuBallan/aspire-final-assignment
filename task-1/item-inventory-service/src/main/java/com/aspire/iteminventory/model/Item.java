package com.aspire.iteminventory.model;

import lombok.Data;

import java.util.List;

@Data
public class Item {

    private long itemNo;

    private String name;

    private String description;

    private int manId;

    private int qty;

    private int price;

    private List<ImageLink> images;

}