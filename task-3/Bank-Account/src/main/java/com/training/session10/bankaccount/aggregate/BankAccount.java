package com.training.session10.bankaccount.aggregate;

import com.training.session10.common.command.account.*;
import com.training.session10.common.event.account.AmountDepositEvent;
import com.training.session10.common.event.account.AmountWithDrawEvent;
import com.training.session10.common.event.account.NewAccountCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Slf4j
public class BankAccount {

    @AggregateIdentifier
    String accountNo;
    private String name;
    private AccountType accountType;
    private Gender gender;
    private int balance;


    public BankAccount() {
    }

    @CommandHandler
    public BankAccount(OpenAccountCommand openAccountCommand){

        this.accountNo = openAccountCommand.getAccountId();

        NewAccountCreatedEvent newAccountCreated = new NewAccountCreatedEvent(openAccountCommand.getAccountId(),
                openAccountCommand.getName(),
                openAccountCommand.getGender(),
                openAccountCommand.getAccountType());

        apply(newAccountCreated);

    }

    @CommandHandler
    public void deposit(DepositCommand depositCommand){
        log.info("this.balance: "+this.balance);

        AmountDepositEvent amountDeposit = new AmountDepositEvent(this.accountNo, depositCommand.getAmmount());

        apply(amountDeposit);
    }

    @CommandHandler
    public void withdraw(WithDrawCommand withDrawCommand){

        log.info("this.balance: "+this.balance);

        if(this.balance < withDrawCommand.getAmmount()){
            throw new RuntimeException(
                    "Balance should be grater than "
                            + withDrawCommand.getAmmount());
        }


        AmountWithDrawEvent amountWithDrawEvent = new AmountWithDrawEvent(this.accountNo, withDrawCommand.getAmmount());

        apply(amountWithDrawEvent);


    }


    @EventSourcingHandler
    public void accountCreated(NewAccountCreatedEvent newAccountCreated){
        this.accountNo=newAccountCreated.getAccountNo();
        name = newAccountCreated.getName();
        accountType = newAccountCreated.getAccountType();
        gender = newAccountCreated.getGender();
        this.balance=0;
    }

    @EventSourcingHandler
    public void amountDeposit(AmountDepositEvent amountDeposit){
        this.balance += amountDeposit.getAmount();
    }

    @EventSourcingHandler
    public void amountWithDraw(AmountWithDrawEvent amountWithDrawEvent){

        this.balance-=amountWithDrawEvent.getAmount();
    }
}
