package com.curso.dcortes.AccountsService.usescases;

import com.curso.dcortes.AccountsService.domain.entities.Account;
import com.curso.dcortes.AccountsService.infrastructure.entities.AccountEntity;
import com.curso.dcortes.AccountsService.infrastructure.mappers.AccountMapper;
import com.curso.dcortes.AccountsService.infrastructure.mappers.AccountMapperImpl;
import com.curso.dcortes.AccountsService.infrastructure.repositories.AccountEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class AccountUseCaseService {

    private final AccountEntityRepository accountEntityRepository;

    private final AccountMapper mapper;

    @Autowired
    public AccountUseCaseService(AccountEntityRepository accountEntityRepository) {
        this.accountEntityRepository = accountEntityRepository;
        this.mapper = new AccountMapperImpl();
    }

    public List<Account> getAccountEntity(Long userId) {
        return accountEntityRepository.findByUserId(userId).stream()
                .map(mapper::mapToDomain)
                .collect(Collectors.toList());
    }

    public Account saveAccountEntity(Long userId) {
        AccountEntity newAccount = new AccountEntity();
        newAccount.setUserId(userId);
        newAccount.setAccountNumber(generateUniqueAccountNumber());
        newAccount.setAccountCard(generateUniqueAccountCard());
        newAccount.setAccountBalance(0.0);
        newAccount.setAccountOpeningDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        return mapper.mapToDomain(accountEntityRepository.save(newAccount));
    }

    // Método para generar aleatoriamente un número de cuenta único
    private String generateUniqueAccountNumber() {
        String generatedAccountNumber;
        do {
            generatedAccountNumber = generateRandomNumber(10);
        } while (!isAccountNumberUnique(generatedAccountNumber));

        return generatedAccountNumber;
    }

    // Método para generar aleatoriamente una tarjeta de cuenta única
    private String generateUniqueAccountCard() {
        String generatedAccountCard;
        do {
            generatedAccountCard = generateRandomNumber(16);
        } while (!isAccountCardUnique(generatedAccountCard));

        return generatedAccountCard;
    }

    private String generateRandomNumber(int length) {
        Random random = new Random();
        StringBuilder number = new StringBuilder();

        for (int i = 0; i < length; i++) {
            number.append(random.nextInt(10));
        }

        return number.toString();
    }

    private boolean isAccountNumberUnique(String accountNumber) {
        return !accountEntityRepository.existsByAccountNumber(accountNumber);
    }

    private boolean isAccountCardUnique(String accountCard) {
        return !accountEntityRepository.existsByAccountCard(accountCard);
    }
}
