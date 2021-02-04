package com.example.samere.godknows.godknows.service;

import com.example.samere.godknows.godknows.entity.Account;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AccountService {

    ResponseEntity getAccounts();

    ResponseEntity getAccountById(Long accountId);

    ResponseEntity addNewAccount(Account account);

    ResponseEntity deleteExistingAccount(Long accountId);

}
