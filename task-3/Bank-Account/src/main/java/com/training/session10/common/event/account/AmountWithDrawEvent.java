package com.training.session10.common.event.account;

import lombok.Value;

@Value
//TODO Event versioning
public class AmountWithDrawEvent extends AbstractAccountEvent {

    private String accountNo;
    private int amount;
}
