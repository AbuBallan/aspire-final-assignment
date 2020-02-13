package com.training.session10.bankaccount.adapter.repository.impl.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name="gender_account_report")
@Data
public class GenderAccountReportEntity {

    @Id
    private String accountType;

    private int male;

    private int female;
}
