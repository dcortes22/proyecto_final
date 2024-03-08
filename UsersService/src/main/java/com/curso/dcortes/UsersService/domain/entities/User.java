package com.curso.dcortes.UsersService.domain.entities;

public record User(Long id, String name, String firstLastName, String secondLastName, String identification,
                   IdentificationType identificationType, String userName, String password) { }
