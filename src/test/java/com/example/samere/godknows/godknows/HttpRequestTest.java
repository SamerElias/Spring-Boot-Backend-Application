package com.example.samere.godknows.godknows;

import com.example.samere.godknows.godknows.auth.JWTAuthenticationFilter;
import com.example.samere.godknows.godknows.entity.Account;
import com.example.samere.godknows.godknows.entity.AccountRequest;
import com.example.samere.godknows.godknows.entity.User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.time.LocalDate;
import java.util.Collections;

import static com.example.samere.godknows.godknows.util.SecurityConstants.HEADER_STRING;
import static com.example.samere.godknows.godknows.util.SecurityConstants.TOKEN_PREFIX;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private static String token;

    private static User user = new User(10L, "samergun", "Testing123@");

    @Test
    public void testGetAccountById() {
        setHeaders();
        Account account = new Account(9911L, "ExistingAccount", "existing@gmail.com", LocalDate.of(1995,07,21), user);
        String accountId = "9911";
        ResponseEntity<Account> response = testRestTemplate
                .getForEntity(getOrRemoveAccountURL(accountId), Account.class);
        assertTrue(response.getStatusCode() == HttpStatus.OK);
        assertEquals(response.getBody(), account);
    }

    @Test
    public void testAddAccount() {
        setHeaders();
        AccountRequest account = new AccountRequest(9898L, "Mari Co", "mari@gmail.com", LocalDate.of(2001,01,22), 10L);
        HttpEntity<AccountRequest> request =
                new HttpEntity<>(account);
        ResponseEntity<String> response = testRestTemplate
                .postForEntity(addAccountURL(), request, String.class);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void removeExistingAccount() {
        setHeaders();
        ResponseEntity response1 = testRestTemplate.getForEntity(getOrRemoveAccountURL("9999"), String.class);
        boolean exists = (response1.getStatusCode() == HttpStatus.OK) ? true : false;
        if (!exists) {
            throw new IllegalStateException(
                    "Account does not exist for it to be removed");
        }
        testRestTemplate.delete(getOrRemoveAccountURL("9999"));
        ResponseEntity response2 = testRestTemplate.getForEntity(getOrRemoveAccountURL("9999"), String.class);
        exists = (response2.getStatusCode() == HttpStatus.OK) ? true : false;
        assertFalse(exists);
    }

    @Test
    public void testAddAccountAndRemoveIt() {
        setHeaders();
        AccountRequest account = new AccountRequest(9090L, "test name", "test@email.com", LocalDate.of(2012, 03, 11), 10L);
        HttpEntity<AccountRequest> request =
                new HttpEntity<>(account);
        ResponseEntity<String> addResponse = testRestTemplate
                .postForEntity(addAccountURL(), request, String.class);
        assertTrue(addResponse.getStatusCode() == HttpStatus.CREATED);
        testRestTemplate.delete(getOrRemoveAccountURL("9999"));
        ResponseEntity deleteResponse = testRestTemplate.getForEntity(getOrRemoveAccountURL("9999"), String.class);
        boolean exists = (deleteResponse.getStatusCode() == HttpStatus.OK) ? true : false;
        assertFalse(exists);
    }

    private String getOrRemoveAccountURL(String accountId) {
        return "http://localhost:" + this.port + "/api/v1/accounts/" + accountId;
    }

    private String addAccountURL() {
        return "http://localhost:" + this.port + "/api/v1/accounts";
    }

    private void setHeaders() {
        token = TOKEN_PREFIX + JWTAuthenticationFilter.createToken();
        testRestTemplate.getRestTemplate().setInterceptors(
                Collections.singletonList((request, body, execution) -> {
                    request.getHeaders()
                            .add(HEADER_STRING, token);
                    return execution.execute(request, body);
                }));
    }

}
