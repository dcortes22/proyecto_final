package com.curso.dcortes.UsersService.usecases;

import com.curso.dcortes.UsersService.domain.entities.User;
import com.curso.dcortes.UsersService.infrastructure.config.JwtUtilities;
import com.curso.dcortes.UsersService.infrastructure.entities.UserEntity;
import com.curso.dcortes.UsersService.infrastructure.mappers.UserMapper;
import com.curso.dcortes.UsersService.infrastructure.mappers.UserMapperImpl;
import com.curso.dcortes.UsersService.infrastructure.repositories.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersUseCaseService {
    private final UserEntityRepository repository;
    private final UserMapper mapper;

    private final AuthenticationManager authenticationManager;

    private final JwtUtilities jwtUtilities;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersUseCaseService(UserEntityRepository repository, AuthenticationManager authenticationManager, JwtUtilities jwtUtilities, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.mapper = new UserMapperImpl();
        this.authenticationManager = authenticationManager;
        this.jwtUtilities = jwtUtilities;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUserEntity(User entity) {
        UserEntity newUser = mapper.mapToEntity(entity);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return mapper.mapToDomain(repository.save(newUser));
    }

    public List<User> getAllUserEntities() {
        List<UserEntity> userEntities = repository.findAll();
        return userEntities.stream()
                .map(mapper::mapToDomain)
                .collect(Collectors.toList());
    }

    public Optional<User> getUserEntityById(String userName) {
        return repository.findByUserName(userName)
                .map(mapper::mapToDomain);
    }

    public void deleteUserEntityById(Long id) {
        repository.deleteById(id);
    }

    public Optional<User> updateUserEntity(Long id, User entity) {
        if (repository.existsById(id)) {
            UserEntity entityToUpdate = mapper.mapToEntity(entity);
            entityToUpdate.setId(id);
            UserEntity updatedEntity = repository.save(entityToUpdate);
            return Optional.ofNullable(mapper.mapToDomain(updatedEntity));
        }
        return Optional.empty();
    }

    public String authenticate(User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.userName(), user.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User loadedUser = repository.findByUserName(user.userName()).map(mapper::mapToDomain).orElseThrow( () -> new UsernameNotFoundException("User not found"));
        List<String> rolesNames = new ArrayList<>();
        return jwtUtilities.generateToken(loadedUser.userName(), rolesNames);
    }

    public Boolean validateToken(String token) {
        return jwtUtilities.validateToken(token);
    }
}
