package com.training.session10.bankaccount.adapter.rest;

import com.training.session10.bankaccount.adapter.rest.dto.AmountDOT;
import com.training.session10.bankaccount.adapter.rest.dto.IDHolder;
import com.training.session10.bankaccount.adapter.rest.dto.NewAccountDTO;
import com.training.session10.common.command.account.AccountType;
import com.training.session10.common.command.account.DepositCommand;
import com.training.session10.common.command.account.OpenAccountCommand;
import com.training.session10.common.command.account.WithDrawCommand;
import com.training.session10.common.model.GenderAccountReport;
import com.training.session10.common.query.GetGenderAccountReport;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.DomainEventMessage;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/bank-account")
public class BankAccountController {


    private final CommandGateway commandGateway;

    private final EventStore eventStore;

    private final QueryGateway queryGateway;

    public BankAccountController(CommandGateway commandGateway, EventStore eventStore, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.eventStore = eventStore;
        this.queryGateway = queryGateway;
    }

    @PostMapping
    public IDHolder creatingAccount(@RequestBody @Valid NewAccountDTO newAccountDTO){
        String accountNo = UUID.randomUUID().toString();
        OpenAccountCommand openAccountCommand = OpenAccountCommand
                .builder()
                .accountId(accountNo)
                .name(newAccountDTO.getName())
                .gender(newAccountDTO.getGender())
                .accountType(newAccountDTO.getAccountType())
                .build();

        commandGateway.sendAndWait(openAccountCommand);

        return new IDHolder(accountNo);

    }

    @PatchMapping("/{accountNo}/deposit")
    public void depositingAmount(@PathVariable("accountNo") String accountNo,
                                 @RequestBody @Valid AmountDOT amountDOT){

        DepositCommand depositCommand = DepositCommand.builder()
                .accountNo(accountNo)
                .ammount(amountDOT.getAmount())
                .build();

        commandGateway.sendAndWait(depositCommand);

    }


    @PatchMapping("/{accountNo}/withdraw")
    public void withdrawAmount(@PathVariable("accountNo") String accountNo,
                                 @RequestBody @Valid AmountDOT amountDOT){

        WithDrawCommand withDrawCommand = WithDrawCommand.builder()
                .accountNo(accountNo)
                .ammount(amountDOT.getAmount())
                .build();

        commandGateway.sendAndWait(withDrawCommand);

    }

    @GetMapping("/{accountNo}/history")
    public List< DomainEventMessage<?>> history(@PathVariable("accountNo") String accountNo){

        return eventStore
                .readEvents(accountNo)
                .asStream()
                .collect(toList());
    }

    @GetMapping("/reports/gender")
    public GenderAccountReport loadingReport(
            @RequestParam("accountType")AccountType accountType

            ) throws ExecutionException, InterruptedException {

        GetGenderAccountReport getGenderAccountReport = new GetGenderAccountReport(accountType);

        return queryGateway
                .query(getGenderAccountReport, ResponseTypes.instanceOf(GenderAccountReport.class))
                .get();
    }

}
