package com.auca.library.service;

import com.auca.library.model.User;
import com.auca.library.model.Membership;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private Map<String, User> users = new HashMap<>();

    public String createAccount(String name, String password) {
        User newUser = new User(name, password);
        users.put(newUser.getId(), newUser);
        return newUser.getId(); // Returns the generated UUID as the user ID
    }

    public void registerMembership(String userId, Membership membershipType) {
        User user = users.get(userId);
        if (user != null) {
            user.setMembership(membershipType);
        }
    }

    public User getUserById(String id) {
        return users.get(id);
    }
}

