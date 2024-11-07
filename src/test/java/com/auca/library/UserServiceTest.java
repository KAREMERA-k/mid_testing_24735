package com.auca.library;

import com.auca.library.model.User;
import com.auca.library.model.Membership;
import com.auca.library.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserService();
    }

    @Test
    public void testCreateAccount() {
        String userId = userService.createAccount("John Doe", "password123");
        User user = userService.getUserById(userId);
        assertNotNull(user);
        assertEquals(userId, user.getId());
    }

    @Test
    public void testRegisterMembership() {
        String userId = userService.createAccount("Jane Doe", "password321");
        userService.registerMembership(userId, Membership.GOLD);
        User user = userService.getUserById(userId);
        assertEquals(Membership.GOLD, user.getMembership());
    }
}

