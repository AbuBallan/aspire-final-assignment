package com.training.session10.common.event.account;

import com.training.session10.common.command.account.AccountType;
import com.training.session10.common.command.account.Gender;
import lombok.Value;

@Value
//TODO Event versioning
public class NewAccountCreatedEvent extends AbstractAccountEvent {

    private String accountNo;

    private String name;

    private Gender gender;

    private AccountType accountType;
}
