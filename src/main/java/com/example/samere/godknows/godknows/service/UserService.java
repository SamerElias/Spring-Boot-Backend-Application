package com.example.samere.godknows.godknows.service;

import com.example.samere.godknows.godknows.entity.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    public ResponseEntity addNewUser(User user);

}
