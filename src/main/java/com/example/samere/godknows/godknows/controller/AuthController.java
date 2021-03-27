package com.example.samere.godknows.godknows.controller;

import com.example.samere.godknows.godknows.config.ApplicationSettings;
import com.example.samere.godknows.godknows.entity.User;
import com.example.samere.godknows.godknows.util.JSON;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/authenticate", produces = "application/json")
public class AuthController {

    @Autowired
    private ApplicationSettings applicationSettings;

//    @PostMapping("")
//    public ResponseEntity authenticate(@RequestBody User user) {
//        return userService.addNewUser(user);
//    }

    @GetMapping("/test")
    public ResponseEntity test() {
        JSONObject body = new JSON("Message", "Connection is okay").getJSON();
        return ResponseEntity.ok(body);
    }

}
