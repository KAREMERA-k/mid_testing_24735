package com.auca.library.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class User extends Person {
    private String password;
    private Membership membership;
    private Map<String, Integer> borrowedBooks = new HashMap<>();

    public User(String name, String password) {
        super(UUID.randomUUID().toString(), name); // Generates a unique ID using UUID
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    public Map<String, Integer> getBorrowedBooks() {
        return borrowedBooks;
    }
}

