package com.aspire.common.querybus;

public interface QueryBus {

    <U> U execute(Query query);

}
