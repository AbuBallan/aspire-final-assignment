package com.training.session10.bankaccount.eventHandler;

import com.training.session10.bankaccount.adapter.repository.GenderAccountRepository;
import com.training.session10.common.event.account.NewAccountCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
//@Profile("event")
public class AccountEventHandlers {

    private final GenderAccountRepository genderAccountRepository;

    public AccountEventHandlers(GenderAccountRepository genderAccountRepository) {
        this.genderAccountRepository = genderAccountRepository;
    }

    @EventHandler
    public void accountCreated(NewAccountCreatedEvent newAccountCreated){
        genderAccountRepository
                .incrementAccount(newAccountCreated.getAccountType(),newAccountCreated.getGender());
    }
}
