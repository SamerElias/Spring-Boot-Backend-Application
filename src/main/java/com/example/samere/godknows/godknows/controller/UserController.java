package com.example.samere.godknows.godknows.controller;

import com.example.samere.godknows.godknows.config.ApplicationSettings;
import com.example.samere.godknows.godknows.entity.User;
import com.example.samere.godknows.godknows.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/users", produces = "application/json")
public class UserController {

    @Autowired
    private ApplicationSettings applicationSettings;

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("")
    public ResponseEntity addNewUser(@RequestBody User user) {
        return userService.addNewUser(user);
    }

}
