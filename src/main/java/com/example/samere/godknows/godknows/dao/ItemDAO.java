package com.example.samere.godknows.godknows.dao;

import com.example.samere.godknows.godknows.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDAO extends
        JpaRepository<CartItem, Integer> {



}
