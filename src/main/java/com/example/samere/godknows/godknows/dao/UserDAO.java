package com.example.samere.godknows.godknows.dao;

import com.example.samere.godknows.godknows.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDAO
        extends JpaRepository<User, Long> {

    @Query("SELECT usr FROM User usr WHERE usr.username = ?1")
    Optional<User> findUserByUsername(String username);

}
