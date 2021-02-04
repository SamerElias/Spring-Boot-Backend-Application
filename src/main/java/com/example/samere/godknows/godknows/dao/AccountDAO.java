package com.example.samere.godknows.godknows.dao;

import com.example.samere.godknows.godknows.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountDAO
        extends JpaRepository<Account, Long> {

    @Query("SELECT acc FROM Account acc WHERE acc.email = ?1")
    Optional<Account> findAccountByEmail(String email);

}
