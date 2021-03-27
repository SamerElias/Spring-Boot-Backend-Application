package com.example.samere.godknows.godknows.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@Entity
@Table(name = "ACCOUNT")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", nullable = false, unique = true, insertable = false, updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate DOB;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Transient
    private Integer age;

    public Account(Long id, String name, String email, LocalDate DOB, User user) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.DOB = DOB;
        this.user = user;
    }

    public Account(String name, String email, LocalDate DOB, User user) {
        this.name = name;
        this.email = email;
        this.DOB = DOB;
        this.user = user;
    }

    public Account(Long id, String name, String email, LocalDate DOB) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.DOB = DOB;
    }

    public Account() {}

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
        return this.DOB == null ? null : Period.between(this.DOB, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void updateAccount(Account account) {
        if(account.getName() != null) {
            this.setName(account.getName());
        }
        if(account.getEmail() != null) {
            this.setEmail(account.getEmail());
        }
        if(account.getDOB() != null) {
            this.setDOB(account.getDOB());
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", DOB=" + DOB +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id.equals(account.id) && Objects.equals(name, account.name) && Objects.equals(email, account.email) && Objects.equals(DOB, account.DOB);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, DOB);
    }
}
