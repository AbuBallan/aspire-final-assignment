package com.ab.common.command;

public interface CommandBus {

    <U> U execute(Command command);

}
