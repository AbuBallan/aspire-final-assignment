package com.training.session10.bankaccount.adapter.repository;

import com.training.session10.common.command.account.AccountType;
import com.training.session10.common.command.account.Gender;
import com.training.session10.common.model.GenderAccountReport;

public interface GenderAccountRepository {

    public GenderAccountReport loadGenderAccountReport(AccountType accountType);

    public void incrementAccount(AccountType accountType, Gender gender);
}
