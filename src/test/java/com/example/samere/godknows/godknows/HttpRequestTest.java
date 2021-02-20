package com.example.samere.godknows.godknows;

import com.example.samere.godknows.godknows.entity.Account;
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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;
//
//    @Test
//    public void testGetAccountById() {
//        Account account = new Account(9999L, "Samer Elias", "samergun", "samere@gmail.com", "Asdasd123@", LocalDate.of(1995,07,21));
//        String accountId = "9999";
//        ResponseEntity<Account> response = testRestTemplate
//                .getForEntity(getOrRemoveAccountURL(accountId), Account.class);
//        assertTrue(response.getStatusCode() == HttpStatus.OK);
//        assertEquals(response.getBody(), account);
//    }
//
//    @Test
//    public void testAddAccount() {
//        Account account = new Account("Mari Co", "marico", "mari@gmail.com", "Asdasd123!", LocalDate.of(2001,01,22));
//        HttpEntity<Account> request =
//                new HttpEntity<>(account);
//        ResponseEntity<String> response = testRestTemplate
//                .postForEntity(addAccountURL(), request, String.class);
//        assertTrue(response.getStatusCode() == HttpStatus.CREATED);
//    }
//
//    @Test
//    public void removeExistingAccount() {
//        ResponseEntity response1 = testRestTemplate.getForEntity(getOrRemoveAccountURL("9999"), String.class);
//        boolean exists = (response1.getStatusCode() == HttpStatus.OK) ? true : false;
//        if (!exists) {
//            throw new IllegalStateException(
//                    "Account does not exist for it to be removed");
//        }
//        testRestTemplate.delete(getOrRemoveAccountURL("9999"));
//        ResponseEntity response2 = testRestTemplate.getForEntity(getOrRemoveAccountURL("9999"), String.class);
//        exists = (response2.getStatusCode() == HttpStatus.OK) ? true : false;
//        assertFalse(exists);
//    }
//
//    @Test
//    public void testAddAccountAndRemoveIt() {
//        Account account = new Account("test name", "testname", "test@email.com", "AsdTesting1!", LocalDate.of(2012, 03, 11));
//        HttpEntity<Account> request =
//                new HttpEntity<>(account);
//        ResponseEntity<String> addResponse = testRestTemplate
//                .postForEntity(addAccountURL(), request, String.class);
//        assertTrue(addResponse.getStatusCode() == HttpStatus.CREATED);
//        testRestTemplate.delete(getOrRemoveAccountURL("9999"));
//        ResponseEntity deleteResponse = testRestTemplate.getForEntity(getOrRemoveAccountURL("9999"), String.class);
//        boolean exists = (deleteResponse.getStatusCode() == HttpStatus.OK) ? true : false;
//        assertFalse(exists);
//    }

    private String getOrRemoveAccountURL(String accountId) {
        return "http://localhost:" + this.port + "/api/v1/accounts/" + accountId;
    }

    private String addAccountURL() {
        return "http://localhost:" + this.port + "/api/v1/accounts";
    }

}
