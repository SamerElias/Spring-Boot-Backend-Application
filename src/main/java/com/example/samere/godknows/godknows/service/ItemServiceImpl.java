package com.example.samere.godknows.godknows.service;

import com.example.samere.godknows.godknows.dao.ItemDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDAO itemDAO;

    private ResponseEntity responseEntity;

    @Override
    public ResponseEntity getItems() {
        responseEntity = responseEntity.ok(itemDAO.findAll());
        return responseEntity;
    }

    @Override
    public ResponseEntity getItem(Integer itemId) {
        return null;
    }
}
