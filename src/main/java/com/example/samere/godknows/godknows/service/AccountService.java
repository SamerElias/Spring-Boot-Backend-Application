package com.example.samere.godknows.godknows.service;

import com.example.samere.godknows.godknows.entity.Account;
import com.example.samere.godknows.godknows.entity.AccountUpdateRequest;
import org.springframework.http.ResponseEntity;

public interface AccountService {

    ResponseEntity getAccounts();

    ResponseEntity getAccountById(Long accountId);

    ResponseEntity addNewAccount(Account account);

    ResponseEntity deleteExistingAccount(Long accountId);

    ResponseEntity updateExistingAccount(Long accountId, AccountUpdateRequest account);

}
