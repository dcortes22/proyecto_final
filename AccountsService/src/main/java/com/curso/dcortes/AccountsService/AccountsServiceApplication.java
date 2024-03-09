package com.curso.dcortes.AccountsService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.curso.dcortes.AccountsService.infrastructure.entities")
public class AccountsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsServiceApplication.class, args);
	}

}
