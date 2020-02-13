package com.aspire.iteminventory.command;

import com.aspire.common.commandbus.Command;
import com.aspire.iteminventory.model.ImageLink;
import lombok.Data;

import java.util.List;

@Data
public class AddItemCommand implements Command {

    private String name;

    private int manId;

    private String description;

    private int qty;

    private int price;

    private List<ImageLink> images;

}
