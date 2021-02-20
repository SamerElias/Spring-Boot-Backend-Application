package com.example.samere.godknows.godknows.entity;

import java.time.LocalDate;

public class AccountUpdateRequest {

    private String name;
    private String email;
    private LocalDate DOB;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDOB() {
        return DOB;
    }

    public void setDOB(LocalDate DOB) {
        this.DOB = DOB;
    }

    @Override
    public String toString() {
        return "AccountUpdateRequest{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", DOB=" + DOB +
                '}';
    }
}
