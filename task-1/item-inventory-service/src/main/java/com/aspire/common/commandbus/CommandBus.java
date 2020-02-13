package com.aspire.common.commandbus;

public interface CommandBus {

    <U> U execute(Command command);

}
