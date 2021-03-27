package com.example.samere.godknows.godknows.controller;

import com.example.samere.godknows.godknows.config.ApplicationSettings;
import com.example.samere.godknows.godknows.service.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/items", produces = "application/json")
public class ItemController {

    @Autowired
    private ApplicationSettings applicationSettings;

    @Autowired
    private ItemServiceImpl itemService;

    @GetMapping("")
    public ResponseEntity getItems(){
        return itemService.getItems();
    }

}
