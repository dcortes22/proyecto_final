package com.curso.dcortes.UsersService.usecases;

import com.curso.dcortes.UsersService.domain.entities.IdentificationType;
import com.curso.dcortes.UsersService.domain.entities.User;
import com.curso.dcortes.UsersService.infrastructure.config.JwtUtilities;
import com.curso.dcortes.UsersService.infrastructure.entities.UserEntity;
import com.curso.dcortes.UsersService.infrastructure.mappers.UserMapper;
import com.curso.dcortes.UsersService.infrastructure.repositories.UserEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class UsersUseCaseServiceTests {
    @Mock
    private UserEntityRepository repository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtilities jwtUtilities;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserMapper mapper;

    @InjectMocks
    private UsersUseCaseService service;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveUserEntity() {
        User user = new User(1L, "test", "test", "test", "11", IdentificationType.NATIONAL, "test", "test");
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setName("test");
        userEntity.setFirstLastName("test");
        userEntity.setSecondLastName("test");
        userEntity.setIdentification("11");
        userEntity.setIdentificationType(IdentificationType.NATIONAL);
        userEntity.setUserName("test");
        userEntity.setPassword("test");
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(repository.save(any())).thenReturn(userEntity);

        when(mapper.mapToEntity(any())).thenReturn(userEntity);
        when(mapper.mapToDomain(any())).thenReturn(user);

        User savedUser = service.saveUserEntity(user);

        assertNotNull(savedUser);
        verify(repository).save(any(UserEntity.class));
    }

    @Test
    void getAllUserEntities() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setName("test");
        userEntity.setFirstLastName("test");
        userEntity.setSecondLastName("test");
        userEntity.setIdentification("11");
        userEntity.setIdentificationType(IdentificationType.NATIONAL);
        userEntity.setUserName("test");
        userEntity.setPassword("test");
        when(repository.findAll()).thenReturn(List.of(userEntity));
        when(mapper.mapToDomain(any())).thenReturn(new User(1L, "test", "test", "test", "11", IdentificationType.NATIONAL, "test", "test"));

        List<User> users = service.getAllUserEntities();

        assertFalse(users.isEmpty());
        verify(repository).findAll();
    }

    @Test
    void getUserEntityById() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setName("test");
        userEntity.setFirstLastName("test");
        userEntity.setSecondLastName("test");
        userEntity.setIdentification("11");
        userEntity.setIdentificationType(IdentificationType.NATIONAL);
        userEntity.setUserName("test");
        userEntity.setPassword("test");
        when(repository.findByUserName(anyString())).thenReturn(Optional.of(userEntity));
        when(mapper.mapToDomain(any())).thenReturn(new User(1L, "test", "test", "test", "11", IdentificationType.NATIONAL, "test", "test"));

        Optional<User> user = service.getUserEntityById("test");

        assertTrue(user.isPresent());
        verify(repository).findByUserName("test");
    }

    @Test
    void deleteUserEntityById() {
        doNothing().when(repository).deleteById(anyLong());

        service.deleteUserEntityById(1L);

        verify(repository).deleteById(1L);
    }

    @Test
    void updateUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setName("test");
        userEntity.setFirstLastName("test");
        userEntity.setSecondLastName("test");
        userEntity.setIdentification("11");
        userEntity.setIdentificationType(IdentificationType.NATIONAL);
        userEntity.setUserName("test2");
        userEntity.setPassword("test");
        when(repository.existsById(anyLong())).thenReturn(true);
        when(repository.save(any(UserEntity.class))).thenReturn(userEntity);
        when(mapper.mapToEntity(any())).thenReturn(userEntity);
        when(mapper.mapToDomain(any())).thenReturn(new User(1L, "test", "test", "test", "11", IdentificationType.NATIONAL, "test2", "test"));

        Optional<User> updatedUser = service.updateUserEntity(1L, new User(1L, "test", "test", "test", "11", IdentificationType.NATIONAL, "test2", "test"));

        assertTrue(updatedUser.isPresent());
        verify(repository).save(any(UserEntity.class));
    }

    @Test
    void authenticate() {
        User user = new User(1L, "test", "test", "test", "11", IdentificationType.NATIONAL, "test", "test");
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setName("test");
        userEntity.setFirstLastName("test");
        userEntity.setSecondLastName("test");
        userEntity.setIdentification("11");
        userEntity.setIdentificationType(IdentificationType.NATIONAL);
        userEntity.setUserName("test");
        userEntity.setPassword("test");
        Authentication authentication = mock(Authentication.class);

        when(repository.findByUserName(anyString())).thenReturn(Optional.of(userEntity));
        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        when(jwtUtilities.generateToken(anyString(), anyList())).thenReturn("token");

        String token = service.authenticate(user);

        assertNotNull(token);
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtUtilities).generateToken(anyString(), anyList());
    }
}
