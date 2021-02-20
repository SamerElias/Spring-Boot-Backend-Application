package com.example.samere.godknows.godknows.service;

import com.example.samere.godknows.godknows.dao.UserDAO;
import com.example.samere.godknows.godknows.entity.User;
import com.example.samere.godknows.godknows.util.JSON;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Collections.emptyList;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

//    // check if the password in accountRequest is valid
//    String password = account.getPassword();
//        if(password != null && !validatePassword(password)) {
//        JSONObject responseBody = new JSON("Error Message", "Invalid password").getJSON();
//        ResponseEntity responseEntity = ResponseEntity
//                .status(HttpStatus.BAD_REQUEST).body(responseBody);
//        return responseEntity;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalAccount = userDAO.findUserByUsername(username);
        if (optionalAccount == null || !optionalAccount.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        User applicationUser = optionalAccount.get();
        return new org.springframework.security.core.userdetails.User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
    }

    public ResponseEntity addNewUser(User user) {
        ResponseEntity responseEntity;
        Optional<User> optionalUser = userDAO.findUserByUsername(user.getUsername());
        if(optionalUser.isPresent()){
            JSONObject responseBody = new JSON("Error Message", "Username taken").getJSON();
            responseEntity = ResponseEntity
                    .status(HttpStatus.BAD_REQUEST).body(responseBody);
            return responseEntity;
        }
        String password = user.getPassword();
        if(!validatePassword(password)) {
            JSONObject responseBody = new JSON("Error Message", "Invalid password").getJSON();
            responseEntity = ResponseEntity
                    .status(HttpStatus.BAD_REQUEST).body(responseBody);
            return responseEntity;
        }
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userDAO.save(user);
        JSONObject responseBody = new JSON("Message", "User created!").getJSON();
//        responseBody.put("Token", );
        responseEntity = ResponseEntity
                .status(HttpStatus.CREATED).body(responseBody);
        return responseEntity;
    }

    private boolean validatePassword(String password) {
        // Regex to check valid password.
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=!])"
                + "(?=\\S+$).{8,20}$";
        // Compile the ReGex
        Pattern p = Pattern.compile(regex);
        // If the password is empty
        // return false
        if (password == null) {
            return false;
        }
        // Pattern class contains matcher() method
        // to find matching between given password
        // and regular expression.
        Matcher m = p.matcher(password);
        // Return if the password
        // matched the ReGex
        return m.matches();
    }

    @Transactional(rollbackFor = Exception.class)
    public String saveDto(User user) {
        user.setPassword(bCryptPasswordEncoder
                .encode(user.getPassword()));
        return "";
    }

}


/*
Regex used for password:

^ represents starting character of the string.
(?=.*[0-9]) represents a digit must occur at least once.
(?=.*[a-z]) represents a lower case alphabet must occur at least once.
(?=.*[A-Z]) represents an upper case alphabet that must occur at least once.
(?=.*[@#$%^&-+=()] represents a special character that must occur at least once.
(?=\\S+$) white spaces don’t allowed in the entire string.
.{8, 20} represents at least 8 characters and at most 20 characters.
$ represents the end of the string.

*/