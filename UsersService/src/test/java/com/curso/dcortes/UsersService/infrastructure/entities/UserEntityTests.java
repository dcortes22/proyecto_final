package com.curso.dcortes.UsersService.infrastructure.entities;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

public class UserEntityTests {
    @Test
    void gettersAndSettersShouldWorkCorrectly() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setName("John");

        assertThat(userEntity.getId()).isEqualTo(1L);
        assertThat(userEntity.getName()).isEqualTo("John");
    }

    @Test
    void userDetailsMethodsShouldWorkCorrectly() {
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword("password");

        Collection<? extends GrantedAuthority> authorities = userEntity.getAuthorities();
        assertThat(authorities).isInstanceOf(ArrayList.class).isEmpty();

        assertThat(userEntity.getUsername()).isNull();
        assertThat(userEntity.isAccountNonExpired()).isTrue();
        assertThat(userEntity.isAccountNonLocked()).isTrue();
        assertThat(userEntity.isCredentialsNonExpired()).isTrue();
        assertThat(userEntity.isEnabled()).isTrue();

        assertThat(userEntity.getPassword()).isEqualTo("password");
    }
}
