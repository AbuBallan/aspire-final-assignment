package com.aspire.iteminventory.query;

import com.aspire.common.querybus.Query;
import lombok.Data;

@Data
public class GetItemsPageQuery implements Query {

    private int pageIndex, pageSize;

}
