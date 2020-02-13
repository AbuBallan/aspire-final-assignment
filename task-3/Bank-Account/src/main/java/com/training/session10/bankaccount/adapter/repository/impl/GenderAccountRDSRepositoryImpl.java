package com.training.session10.bankaccount.adapter.repository.impl;

import com.training.session10.bankaccount.adapter.repository.GenderAccountRepository;
import com.training.session10.bankaccount.adapter.repository.impl.entity.GenderAccountReportEntity;
import com.training.session10.common.command.account.AccountType;
import com.training.session10.common.command.account.Gender;
import com.training.session10.common.model.GenderAccountReport;
import org.springframework.stereotype.Component;

@Component
public class GenderAccountRDSRepositoryImpl implements
        GenderAccountRepository {

    private final GenderAccountJPARepository genderAccountJPARepository;

    public GenderAccountRDSRepositoryImpl(GenderAccountJPARepository genderAccountJPARepository) {
        this.genderAccountJPARepository = genderAccountJPARepository;

    }

    @Override
    public GenderAccountReport loadGenderAccountReport(AccountType accountType) {

        return genderAccountJPARepository
                .findById(accountType.name())
                .map(this::convertToModel)
                .orElse(this.emptyReport(accountType));

    }

    @Override
    public void incrementAccount(AccountType accountType, Gender gender) {

        if(gender.equals(Gender.MALE)){
            genderAccountJPARepository.incrementMale(accountType.name());
        }else{
            genderAccountJPARepository.incrementFemale(accountType.name());

        }
    }

    private GenderAccountReport emptyReport(AccountType accountType) {

        return GenderAccountReport
                .builder()
                .accountType(accountType)
                .build();
    }

    private  GenderAccountReport convertToModel(GenderAccountReportEntity genderAccountReportEntity) {


        return GenderAccountReport
                .builder()
                .femaleCount(genderAccountReportEntity.getFemale())
                .maleCount(genderAccountReportEntity.getMale())
                .accountType(AccountType.valueOf(genderAccountReportEntity.getAccountType()))
                .build();
    }
}
