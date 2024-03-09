package com.curso.dcortes.AccountsService.infrastructure.mappers;

import com.curso.dcortes.AccountsService.domain.entities.Account;
import com.curso.dcortes.AccountsService.infrastructure.entities.AccountEntity;
import org.mapstruct.Mapper;

@Mapper
public interface AccountMapper {
    AccountEntity mapToEntity(Account account);
    Account mapToDomain(AccountEntity accountEntity);
}
