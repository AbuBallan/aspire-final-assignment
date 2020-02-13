package com.ab.common.query;

public interface QueryBus {

    <U> U execute(Query query);

}
