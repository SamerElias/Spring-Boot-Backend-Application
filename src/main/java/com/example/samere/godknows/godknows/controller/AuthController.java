package com.example.samere.godknows.godknows.controller;

import com.example.samere.godknows.godknows.config.ApplicationSettings;
import com.example.samere.godknows.godknows.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/authenticate", produces = "application/json")
public class AuthController {

    @Autowired
    private ApplicationSettings applicationSettings;

//    @PostMapping("")
//    public ResponseEntity authenticate(@RequestBody User user) {
//        return userService.addNewUser(user);
//    }

}
