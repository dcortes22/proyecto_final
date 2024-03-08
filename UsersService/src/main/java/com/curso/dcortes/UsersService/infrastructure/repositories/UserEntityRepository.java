package com.curso.dcortes.UsersService.infrastructure.repositories;

import com.curso.dcortes.UsersService.infrastructure.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    // Try to find by Username
    Optional<UserEntity> findByUserName(String userName);
}
