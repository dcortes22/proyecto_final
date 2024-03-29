package com.curso.dcortes.AccountsService.presentation.controllers;

import com.curso.dcortes.AccountsService.domain.entities.Account;
import com.curso.dcortes.AccountsService.usescases.AccountUseCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountsController {

    private final AccountUseCaseService accountUseCaseService;

    @Autowired
    public AccountsController(AccountUseCaseService accountUseCaseService) {
        this.accountUseCaseService = accountUseCaseService;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Account>> getAccount(@PathVariable Long id) {
        return new ResponseEntity<>(accountUseCaseService.getAccountEntity(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Account> createAccount() {
        return new ResponseEntity<>(accountUseCaseService.saveAccountEntity(323L), HttpStatus.CREATED);
    }
}
