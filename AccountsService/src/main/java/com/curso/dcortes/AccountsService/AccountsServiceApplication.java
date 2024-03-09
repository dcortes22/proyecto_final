package com.curso.dcortes.AccountsService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.curso.dcortes.AccountsService.infrastructure.entities")
@EnableJpaRepositories("com.curso.dcortes.AccountsService.infrastructure.repositories")
public class AccountsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsServiceApplication.class, args);
	}

}
