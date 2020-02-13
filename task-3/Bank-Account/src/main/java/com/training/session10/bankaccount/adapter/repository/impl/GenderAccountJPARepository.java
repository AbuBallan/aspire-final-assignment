package com.training.session10.bankaccount.adapter.repository.impl;

import com.training.session10.bankaccount.adapter.repository.impl.entity.GenderAccountReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface GenderAccountJPARepository extends JpaRepository<GenderAccountReportEntity,String> {

    @Transactional
    @Modifying

    @Query("UPDATE GenderAccountReportEntity r set male= r.male + 1 " +
            "where accountType = :accountType")
    public void incrementMale(@Param("accountType") String accountType);

    @Transactional
    @Modifying

    @Query("UPDATE GenderAccountReportEntity r set female= r.female + 1 " +
            "where r.accountType=:accountType")
    public void incrementFemale(@Param("accountType") String accountType);

}
