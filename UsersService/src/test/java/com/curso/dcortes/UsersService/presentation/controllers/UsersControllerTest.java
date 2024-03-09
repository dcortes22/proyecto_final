package com.curso.dcortes.UsersService.presentation.controllers;

import com.curso.dcortes.UsersService.domain.entities.IdentificationType;
import com.curso.dcortes.UsersService.domain.entities.User;
import com.curso.dcortes.UsersService.infrastructure.config.JwtUtilities;
import com.curso.dcortes.UsersService.infrastructure.mappers.UserMapper;
import com.curso.dcortes.UsersService.infrastructure.repositories.UserEntityRepository;
import com.curso.dcortes.UsersService.usecases.UsersUseCaseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class UsersControllerTest {

    @Autowired
    private MockMvc mockMvc;

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

    @Mock
    private UsersUseCaseService usersService;

    @MockBean
    private RabbitTemplate rabbitTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUser_ShouldReturnCreatedUser() throws Exception {
        User user = new User(1L, "test", "test", "test", "11", IdentificationType.NATIONAL, "test", "test");
        User createdUser = new User(1L, "test", "test", "test", "11", IdentificationType.NATIONAL, "test", "test");

        when(usersService.saveUserEntity(user)).thenReturn(createdUser);

        mockMvc.perform(post("/api/users/register")
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
