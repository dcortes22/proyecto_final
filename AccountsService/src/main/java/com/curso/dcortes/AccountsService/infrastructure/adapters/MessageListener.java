package com.curso.dcortes.AccountsService.infrastructure.adapters;

import com.curso.dcortes.AccountsService.domain.entities.Account;
import com.curso.dcortes.AccountsService.usescases.AccountUseCaseService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
    private  final AccountUseCaseService accountUseCaseService;
    @Autowired
    public MessageListener(AccountUseCaseService accountUseCaseService) {
        this.accountUseCaseService = accountUseCaseService;
    }

    @RabbitListener(queues = "accounts")
    public void listener(String message) {
        try {
            Long id = Long.valueOf(message);
            accountUseCaseService.saveAccountEntity(id);
        } catch (NumberFormatException ex) {
            System.out.println("Invalid message id");
        }
    }
}
