package com.aspire.iteminventory.adapter.rest.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class ItemDTO {

    @Min(0)
    private long itemNo;

    @NotEmpty
    private String name;

    @Min(1)
    private int manId;

    private String description;

    @Min(0)
    private int qty;

    @Min(0)
    private int price;

    private List<ImageLinkDTO> images;

}