package com.aspire.iteminventory.query;

import com.aspire.common.querybus.Query;
import lombok.Data;

@Data
public class GetItemQuery implements Query {

    private long itemId;

}
