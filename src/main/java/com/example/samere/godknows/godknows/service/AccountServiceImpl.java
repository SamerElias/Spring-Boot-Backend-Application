package com.example.samere.godknows.godknows.service;

import com.example.samere.godknows.godknows.dao.AccountDAO;
import com.example.samere.godknows.godknows.dao.UserDAO;
import com.example.samere.godknows.godknows.entity.Account;
import com.example.samere.godknows.godknows.entity.User;
import com.example.samere.godknows.godknows.entity.AccountResponse;
import com.example.samere.godknows.godknows.entity.AccountUpdateRequest;
import com.example.samere.godknows.godknows.util.AccountEmailValidator;
import com.example.samere.godknows.godknows.util.JSON;
import org.apache.commons.validator.routines.EmailValidator;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
public class AccountServiceImpl implements AccountService {

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
        List<AccountResponse> accountResponseList =
                AccountResponse.prepAccountListResponse(accounts);
        return new ResponseEntity(accountResponseList, HttpStatus.OK);
    }

    public ResponseEntity getAccountById(Long accountId) {
        Optional<Account> optionalAccount = accountDAO.findById(accountId);
        if(optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            AccountResponse accountResponse = AccountResponse.prepAccountResponse(account);
            return new ResponseEntity(accountResponse, HttpStatus.OK);
        }
        JSONObject responseBody = new JSON("Error Message",
                "Account with id=" + accountId + " does not exist").getJSON();
        return new ResponseEntity(responseBody, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity addNewAccount(Account account) {
        ResponseEntity responseEntity;
        // in case of unexpected case where id was given, override it
        account.setId(null);
        EmailValidator emailValidator = new AccountEmailValidator(true);
        if(account.getEmail() == null || !emailValidator.isValid(account.getEmail())) {
            JSONObject responseBody = new JSON("Error Message", "Email invalid").getJSON();
            responseEntity = ResponseEntity
                    .status(HttpStatus.BAD_REQUEST).body(responseBody);
            return responseEntity;
        }
        account.setEmail(account.getEmail().trim());
        if(checkEmailExists(account.getEmail())) {
            JSONObject responseBody = new JSON("Error Message", "Email taken").getJSON();
            responseEntity = ResponseEntity
                    .status(HttpStatus.BAD_REQUEST).body(responseBody);
            return responseEntity;
        }
        accountDAO.save(account);
        JSONObject responseBody = new JSON("Message", "Account added successfully").getJSON();
        responseEntity = ResponseEntity
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

    public ResponseEntity updateExistingAccount(Long accountId, AccountUpdateRequest account) {
        // fetch account by id
        Optional<Account> optionalAccount = accountDAO.findById(accountId);
        if(!optionalAccount.isPresent()) {
            JSONObject responseBody = new JSON("Error Message",
                    "Account with id=" + accountId + " does not exist").getJSON();
            return new ResponseEntity(responseBody, HttpStatus.BAD_REQUEST);
        }
        Account currentAccount = optionalAccount.get();
        // check if the email in accountRequest is taken
        if(account.getEmail() != null
                && !account.getEmail().equals(currentAccount.getEmail())
                && checkEmailExists(account.getEmail())) {
            JSONObject responseBody = new JSON("Error Message", "Email taken").getJSON();
            ResponseEntity responseEntity = ResponseEntity
                    .status(HttpStatus.BAD_REQUEST).body(responseBody);
            return responseEntity;
        }
        // update existing account with accountRequest fields and save it to DB
        Account updatedAccount = updateAccount(currentAccount, account);
        accountDAO.save(updatedAccount);
        JSONObject responseBody = new JSON("Message", "Account updated successfully").getJSON();
        return ResponseEntity.ok(responseBody);
    }

    private Boolean checkEmailExists(String email) {
        Optional<Account> optionalAccount =
                accountDAO.findAccountByEmail(email);
        if(optionalAccount.isPresent()) {
            return true;
        }
        return false;
    }

    private Account updateAccount(Account account, AccountUpdateRequest accountRequest) {
        String name = accountRequest.getName();
        String email = accountRequest.getEmail();
        LocalDate dob = accountRequest.getDOB();
        if(name != null) {
            account.setName(name);
        }
        if(email != null) {
            account.setEmail(email);
        }
        if(dob != null) {
            account.setDOB(dob);
        }
        return account;
    }

}
