package com.example.samere.godknows.godknows;

import com.example.samere.godknows.godknows.auth.JWTAuthenticationFilter;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.example.samere.godknows.godknows.util.SecurityConstants.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TokenAuthenticationServiceTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldNotAllowAccessToUnauthenticatedUsers() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(TEST_URL)).andExpect(status().isForbidden());
    }

    @Test
    public void shouldGenerateAuthToken() throws Exception {
        String token = TOKEN_PREFIX + JWTAuthenticationFilter.createToken();

        assertNotNull(token);
        mvc.perform(MockMvcRequestBuilders.get(TEST_URL).header(HEADER_STRING, token)).andExpect(status().isOk());
    }

}