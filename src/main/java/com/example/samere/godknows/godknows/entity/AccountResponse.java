package com.example.samere.godknows.godknows.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AccountResponse {

    private Long id;
    private String name;
    private String email;
    private LocalDate DOB;
    private Integer age;

    public AccountResponse(Long id, String name, String email, LocalDate DOB, Integer age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.DOB = DOB;
        this.age = age;
    }

    public static AccountResponse prepAccountResponse(Account account) {
        return new AccountResponse(account.getId(), account.getName(),
                account.getEmail(), account.getDOB(), account.getAge());
    }

    public static List<AccountResponse> prepAccountListResponse(List<Account> accounts) {
        List<AccountResponse> accountResponseList = new ArrayList<>();
        for(Account account : accounts) {
            accountResponseList.add(prepAccountResponse(account));
        }
        return accountResponseList;
    }

    @Override
    public String toString() {
        return "AccountResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", DOB=" + DOB +
                ", age=" + age +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountResponse that = (AccountResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(DOB, that.DOB) && Objects.equals(age, that.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, DOB, age);
    }
}
