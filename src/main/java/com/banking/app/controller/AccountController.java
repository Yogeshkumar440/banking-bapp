package com.banking.app.controller;

import com.banking.app.dto.AccountDto;
import com.banking.app.dto.TransactionDto;
import com.banking.app.dto.TransferFundDto;
import com.banking.app.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // Add account REST API
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        logger.info("Received request to create an account: {}",accountDto);
        AccountDto createdAccount = accountService.createAccount(accountDto);
        logger.info("Account created successfully with ID: {} ",createdAccount.id());
        return new ResponseEntity<>(createdAccount,HttpStatus.CREATED);
    }

    // Get account REST API
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        logger.info("Fetching account with ID: {} ",id);
        AccountDto accountDto = accountService.getAccountById(id);
        logger.info("Account details retrieved: {} ",accountDto);
        return ResponseEntity.ok(accountDto);
    }

    // Deposit REST API
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id,@RequestBody Map<String,Double> request){
        logger.info("Deposit request received: Account ID = {}, Amount = {}",id,request.get("amount"));
        AccountDto accountDto = accountService.deposit(id, request.get("amount"));
        logger.info("Deposit successful: New Balance = {}", accountDto.balance());
        return ResponseEntity.ok(accountDto);
    }

    // Withdraw REST API
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id,@RequestBody Map<String, Double> request){
        logger.info("Withdraw request received: Account ID = {}, Amount = {}",id, request.get("amount"));
        double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id,amount);
        logger.info("Withdraw successful: New Balance = {} ",accountDto.balance());
        return ResponseEntity.ok(accountDto);
    }

    // Get All Accounts REST API
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        logger.info("Fetching all accounts");
        List<AccountDto> allAccounts = accountService.getAllAccounts();
        return ResponseEntity.ok(allAccounts);
    }

    // Delete Account REST API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        logger.warn("Request to delete account ID: {} ",id);
        accountService.deleteAccount(id);
        logger.info("Account with ID {} deleted successfully",id);;
        return ResponseEntity.ok("Account is deleted successfully");
    }

    // Build transfer REST API
    @PostMapping("/transfer")
    public ResponseEntity<String> transferFund(@RequestBody TransferFundDto transferFundDto){
        logger.info("Fund transfer request: From ID = {}, To ID = {}, Amount = {}",
                transferFundDto.fromAccountId(), transferFundDto.toAccountId(), transferFundDto.amount());
        accountService.transferFunds(transferFundDto);
        logger.info("Transfer successful: {} -> {} for amount {}",
                transferFundDto.fromAccountId(), transferFundDto.toAccountId(), transferFundDto.amount());
        return ResponseEntity.ok("Transfer Successfully");
    }

    // Build transactions REST API
    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<TransactionDto>> fetchAccountTransaction(@PathVariable("id") Long accountId){
        logger.info("Fetching transactions for account ID: {} ",accountId);
        List<TransactionDto> transactions = accountService.getAccountTransaction(accountId);
        return ResponseEntity.ok(transactions);
    }
}
