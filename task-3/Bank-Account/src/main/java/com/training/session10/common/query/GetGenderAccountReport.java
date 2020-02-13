package com.training.session10.common.query;

import com.training.session10.common.command.account.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetGenderAccountReport {

    private AccountType accountType;
}
