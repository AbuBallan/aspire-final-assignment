package com.aspire.iteminventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan("com.aspire.*")
public class ItemInventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemInventoryServiceApplication.class, args);
	}

}
