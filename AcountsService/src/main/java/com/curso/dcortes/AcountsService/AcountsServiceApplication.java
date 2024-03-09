package com.curso.dcortes.AcountsService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.curso.dcortes.AccountsService.infrastructure.entities")
public class AcountsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcountsServiceApplication.class, args);
	}

}
