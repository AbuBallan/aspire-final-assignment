CREATE TABLE `training`.`gender_account_report` (
  `account_type` VARCHAR(20) NOT NULL,
  `male` INT NULL,
  `female` INT NULL,
  PRIMARY KEY (`account_type`));


INSERT INTO training.gender_account_report

 VALUES("SAVING",0,0);

 INSERT INTO training.gender_account_report

 VALUES("CURRENT",0,0);


 INSERT INTO training.gender_account_report

 VALUES("SALARY",0,0);