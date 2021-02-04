package com.example.samere.godknows.godknows.service;

import com.example.samere.godknows.godknows.dao.AccountDAO;
import com.example.samere.godknows.godknows.entity.Account;
import com.example.samere.godknows.godknows.util.AccountEmailValidator;
import com.example.samere.godknows.godknows.util.JSON;
import org.apache.commons.validator.routines.EmailValidator;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements  AccountService {

    @Autowired
    private AccountDAO accountDAO;

    public ResponseEntity getAccounts() {
        List<Account> accounts = accountDAO.findAll();
        if(accounts == null) {
            JSONObject responseBody = new JSON("Error Message", "Server Error").getJSON();
            ResponseEntity responseEntity = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
            return responseEntity;
        }
        return new ResponseEntity(accounts, HttpStatus.OK);
    }

    public ResponseEntity getAccountById(Long accountId) {
        Optional<Account> optional = accountDAO.findById(accountId);
        if(optional.isPresent()) {
            return new ResponseEntity(optional.get(), HttpStatus.OK);
        }
        JSONObject responseBody = new JSON("Error Message",
                "Account with id=" + accountId + " does not exist").getJSON();
        return new ResponseEntity(responseBody, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity addNewAccount(Account account) {
        // in case of unexpected case where id was given, override it
        account.setId(null);
        EmailValidator emailValidator = new AccountEmailValidator(true);
        if(account.getEmail() == null || !emailValidator.isValid(account.getEmail())) {
            JSONObject responseBody = new JSON("Error Message", "Email invalid").getJSON();
            ResponseEntity responseEntity = ResponseEntity
                    .status(HttpStatus.BAD_REQUEST).body(responseBody);
            return responseEntity;
        }
        account.setEmail(account.getEmail().trim());
        Optional<Account> optionalAccount =
                accountDAO.findAccountByEmail(account.getEmail());
        if(optionalAccount.isPresent()) {
            JSONObject responseBody = new JSON("Error Message", "Email taken").getJSON();
            ResponseEntity responseEntity = ResponseEntity
                    .status(HttpStatus.BAD_REQUEST).body(responseBody);
            return responseEntity;        }
        accountDAO.save(account);
        JSONObject responseBody = new JSON("Message", "Account added successfully").getJSON();
        ResponseEntity responseEntity = ResponseEntity
                .status(HttpStatus.CREATED).body(responseBody);
        return responseEntity;
    }

    public ResponseEntity deleteExistingAccount(Long accountId) {
        try {
            accountDAO.deleteById(accountId);
        } catch(Exception e) {
            JSONObject responseBody = new JSON("Error Message",
                    "Account with id=" + accountId + " does not exist").getJSON();
            return new ResponseEntity(responseBody, HttpStatus.BAD_REQUEST);
        }
        // if account not found, then delete operation was successful
        if(getAccountById(accountId).getStatusCode().equals(HttpStatus.NOT_FOUND)) {
            JSONObject responseBody = new JSON("Message", "Account removed successfully").getJSON();
            return new ResponseEntity(responseBody, HttpStatus.OK);
        }
        JSONObject responseBody = new JSON("Error Message", "Delete operation failed").getJSON();
        return new ResponseEntity(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
