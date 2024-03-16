package com.curso.dcortes.AccountsService.infrastructure.repositories;

import com.curso.dcortes.AccountsService.infrastructure.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountEntityRepository extends JpaRepository<AccountEntity, Long> {

    boolean existsByAccountNumber(String accountNumber);
    boolean existsByAccountCard(String accountCard);
    List<AccountEntity> findByUserId(Long userId);
}
