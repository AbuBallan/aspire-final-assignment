package com.ab.inventory.events;

import com.ab.inventory.model.ImageLink;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class ItemAddedEvent {

    private String itemNo;

    private String name;

    private String description;

    private int manId;

    private int qty;

    private int price;

    private List<ImageLink> images;

    @Builder.Default
    private String type = "ItemAddedEvent";

}
