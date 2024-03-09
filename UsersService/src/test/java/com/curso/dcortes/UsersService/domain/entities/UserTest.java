package com.curso.dcortes.UsersService.domain.entities;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {
    @Test
    void userRecordShouldHaveGetters() {
        User user = new User(1L, "John", "Doe", "Smith", "12345", IdentificationType.NATIONAL, "john.doe", "password");

        assertThat(user.id()).isEqualTo(1L);
        assertThat(user.name()).isEqualTo("John");
        assertThat(user.firstLastName()).isEqualTo("Doe");
        assertThat(user.secondLastName()).isEqualTo("Smith");
        assertThat(user.identification()).isEqualTo("12345");
        assertThat(user.identificationType()).isEqualTo(IdentificationType.NATIONAL);
        assertThat(user.userName()).isEqualTo("john.doe");
        assertThat(user.password()).isEqualTo("password");
    }
}
