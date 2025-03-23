package com.banking.app.dto;
public record AccountDto(Long id,
                         String accountHolderName,
                         double balance) {
}