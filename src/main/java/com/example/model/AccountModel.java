package com.example.model;

import java.sql.Timestamp;
import java.util.Date;

public class AccountModel extends AbstractModel {
    private String username, password, email, phoneNumber;
    private int role = 0;

    public AccountModel() {

    }

    public AccountModel(int id, String username, String password, String email, String phoneNumber, int role, Timestamp createdAt, Timestamp updatedAt) {
        super(id, createdAt, updatedAt);
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
