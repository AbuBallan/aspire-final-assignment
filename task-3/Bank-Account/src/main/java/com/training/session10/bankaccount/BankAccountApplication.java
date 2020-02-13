package com.training.session10.bankaccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.training")
/**
 * mvn -Dflyway.user=root -Dflyway.password=root -Dflyway.schemas=training -Dflyway.url=jdbc:mysql://localhost:3306/training flyway:migrate
 * mvn -Dflyway.user=root -Dflyway.password=root -Dflyway.schemas=training -Dflyway.url=jdbc:mysql://localhost:3306/training flyway:clean
 */
public class BankAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountApplication.class, args);
	}

}
