package com.twu.biblioteca;

import java.util.HashMap;

public class User {
    private String password;
    private String name;
    private String email;
    private String number;

    public User(String password, String name, String email, String number) throws IllegalArgumentException {
        if(password.isEmpty()) {
            throw new IllegalArgumentException("Password can't be empty");
        }
        if(name.isEmpty()) {
            throw new IllegalArgumentException("Name can't be empty");
        }
        if(email.isEmpty()) {
            throw new IllegalArgumentException("Email can't be empty");
        }
        if(number.isEmpty()) {
            throw new IllegalArgumentException("Number can't be empty");
        }

        this.password = password;
        this.name = name;
        this.email = email;
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getNumber() {
        return number;
    }

}
