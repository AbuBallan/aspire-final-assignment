package com.training.session10.common.model;

import com.training.session10.common.command.account.AccountType;
import com.training.session10.common.command.account.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor

public class GenderAccountReport {

    private AccountType accountType;

    private int maleCount;

    private int femaleCount;
}
