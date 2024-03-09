package com.curso.dcortes.AccountsService.domain.entities;

import java.util.Date;

public record Account(Long id, Long userId, String accountNumber, String accountCard, Double accountBalance, Date accountOpeningDate) { }
