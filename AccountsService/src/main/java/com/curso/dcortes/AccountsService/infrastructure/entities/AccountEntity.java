package com.curso.dcortes.AccountsService.infrastructure.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.Date;

@Entity
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Column(name = "account_number", length = 10)
    @Size(max = 10, message = "The account number must not be more than 10 digits")
    private String accountNumber;

    @Column(name = "account_card", length = 16)
    @Size(max = 16, message = "The account card must not be more than 16 digits")
    private String accountCard;

    private Double accountBalance;

    private Date accountOpeningDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountCard() {
        return accountCard;
    }

    public void setAccountCard(String accountCard) {
        this.accountCard = accountCard;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Date getAccountOpeningDate() {
        return accountOpeningDate;
    }

    public void setAccountOpeningDate(Date accountOpeningDate) {
        this.accountOpeningDate = accountOpeningDate;
    }
}
