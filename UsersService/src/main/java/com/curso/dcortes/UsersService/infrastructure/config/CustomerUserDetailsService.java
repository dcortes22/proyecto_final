package com.curso.dcortes.UsersService.infrastructure.config;

import com.curso.dcortes.UsersService.infrastructure.mappers.UserMapperImpl;
import com.curso.dcortes.UsersService.infrastructure.repositories.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomerUserDetailsService implements UserDetailsService {

    private final UserEntityRepository repository;

    @Autowired
    public CustomerUserDetailsService(UserEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUserName(username).orElseThrow(()-> new UsernameNotFoundException("User not found "));
    }
}
