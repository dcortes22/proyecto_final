package com.curso.dcortes.UsersService.presentation.controllers;

import com.curso.dcortes.UsersService.domain.entities.IdentificationType;
import com.curso.dcortes.UsersService.domain.entities.User;
import com.curso.dcortes.UsersService.usecases.UsersUseCaseService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersUseCaseService usersService;

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public UsersController(UsersUseCaseService usersService, RabbitTemplate rabbitTemplate) {
        this.usersService = usersService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = usersService.saveUserEntity(user);
        rabbitTemplate.convertAndSend("users", "register", createdUser.id());
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public String authenticate(@RequestBody User user) {
        return usersService.authenticate(user);
    }

    @GetMapping("/{userName}")
    public ResponseEntity<User> getUser(@PathVariable String userName) {
        return usersService.getUserEntityById(userName)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/accounts/{userName}")
    public ResponseEntity<Object> getAccount(@PathVariable String userName) {
        Optional<User> optionalUser = usersService.getUserEntityById(userName);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return usersService.getAccount(user.id())
                    .map(response -> {
                        System.out.println(response);
                        return ResponseEntity.ok().body(response);
                    })
                    .defaultIfEmpty(ResponseEntity.notFound().build())
                    .block();
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/validateToken")
    public ResponseEntity<Boolean> validateToken(HttpServletRequest request) {
        try {
            String authorizationHeader = request.getHeader("Authorization");
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                String token = authorizationHeader.substring(7);
                boolean isValidToken = usersService.validateToken(token);
                if (isValidToken) {
                    return ResponseEntity.ok(true);
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }
    }
}
