package com.ab.cart.query;

import com.ab.common.query.Query;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemExistsQuery implements Query {

    private String itemNo;

}
