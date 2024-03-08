package com.curso.dcortes.UsersService.domain.entities;

public enum IdentificationType {
    NATIONAL("NATIONAL"),
    PASSPORT("PASSPORT");

    private String name;

    IdentificationType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
