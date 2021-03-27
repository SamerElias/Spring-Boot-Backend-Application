package com.example.samere.godknows.godknows.service;

import org.springframework.http.ResponseEntity;

public interface ItemService {

    public ResponseEntity getItems();

    public ResponseEntity getItem(Integer itemId);

}
