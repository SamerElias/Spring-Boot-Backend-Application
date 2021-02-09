package com.example.samere.godknows.godknows.controller;

import com.example.samere.godknows.godknows.config.ApplicationSettings;
import com.example.samere.godknows.godknows.entity.Account;
import com.example.samere.godknows.godknows.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/v1/accounts", produces = "application/json")
public class AccountController {

    @Autowired
    private ApplicationSettings applicationSettings;

    @Autowired
    private AccountServiceImpl accountService;

    @GetMapping("")
    public ResponseEntity getAccounts() {
        return accountService.getAccounts();
    }

    @GetMapping("/{accountId}")
    public ResponseEntity getAccount(@PathVariable Long accountId) {
        return accountService.getAccountById(accountId);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registerAccount(@RequestBody Account account) {
        return accountService.addNewAccount(account);

    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity deleteAccount(@PathVariable Long accountId) {
        return accountService.deleteExistingAccount(accountId);
    }

}
