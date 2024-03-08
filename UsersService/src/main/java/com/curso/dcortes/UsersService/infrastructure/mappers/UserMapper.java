package com.curso.dcortes.UsersService.infrastructure.mappers;

import com.curso.dcortes.UsersService.domain.entities.User;
import com.curso.dcortes.UsersService.infrastructure.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {
    @Mapping(target = "authorities", ignore = true)
    UserEntity mapToEntity(User user);
    User mapToDomain(UserEntity userEntity);
}
