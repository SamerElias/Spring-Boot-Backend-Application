package com.example.samere.godknows.godknows;

import com.example.samere.godknows.godknows.entity.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testGetAccountById() {
        Account account = new Account(9999L, "Samer Elias", "samere@gmail.com", LocalDate.of(1995,07,21));
        String accountId = "9999";
        ResponseEntity<Account> response = testRestTemplate
                .getForEntity(getAccountURL(accountId), Account.class);
        assertTrue(response.getStatusCode() == HttpStatus.OK);
        assertEquals(response.getBody(), account);
    }

    @Test
    public void testAddAccount() {
        Account account = new Account(9998L, "Mari Co", "mari@gmail.com", LocalDate.of(2001,01,22));
        HttpEntity<Account> request =
                new HttpEntity<>(account);
        ResponseEntity<String> response = testRestTemplate
                .postForEntity(addAccountURL(), request, String.class);
        assertTrue(response.getStatusCode() == HttpStatus.CREATED);

    }

    @Test
    public void removeExistingAccount() {
        Account response = testRestTemplate.getForObject(getAccountURL("9999"), Account.class);
        boolean exists = (response == null) ? false : true;
        if (!exists) {
            throw new IllegalStateException(
                    "Account does not exist for it to be removed");
        }
        // TODO proceed with removing the account
    }

    @Test
    public void testAddAccountAndRemoveIt() {
        Account account = new Account("test name", "test@email.com", LocalDate.of(2012, 03, 11));
    }

    private String getAccountURL(String accountId) {
        return "http://localhost:" + this.port + "/api/v1/accounts/" + accountId;
    }

    private String addAccountURL() {
        return "http://localhost:" + this.port + "/api/v1/accounts";
    }

}
