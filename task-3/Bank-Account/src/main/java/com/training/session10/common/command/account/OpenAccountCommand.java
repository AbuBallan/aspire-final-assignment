package com.training.session10.common.command.account;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class OpenAccountCommand  extends AbstractAccountCommand{

    @TargetAggregateIdentifier
    private String accountId;

    private String name;

    private AccountType accountType;

    private Gender gender;
}
