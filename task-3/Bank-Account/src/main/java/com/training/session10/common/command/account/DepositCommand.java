package com.training.session10.common.command.account;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.Min;

@Data
@Builder
public class DepositCommand extends AbstractAccountCommand{

    @TargetAggregateIdentifier
    private String accountNo;

    @Min(0)
    private int ammount;
}
