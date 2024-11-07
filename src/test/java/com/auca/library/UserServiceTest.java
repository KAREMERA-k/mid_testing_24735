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
        // Test creating an account with unique ID generation
        String userId1 = userService.createAccount("John Doe", "password123");
        String userId2 = userService.createAccount("Jane Smith", "password321");

        assertNotNull(userId1, "User ID should not be null");
        assertNotNull(userId2, "User ID should not be null");
        assertNotEquals(userId1, userId2, "User IDs should be unique");

        User user1 = userService.getUserById(userId1);
        User user2 = userService.getUserById(userId2);

        assertEquals("John Doe", user1.getName());
        assertEquals("Jane Smith", user2.getName());
        assertEquals("password123", user1.getPassword());
        assertEquals("password321", user2.getPassword());
    }

    @Test
    public void testGetUserById() {
        // Verify user retrieval by ID after account creation
        String userId = userService.createAccount("Alice Cooper", "securePass");
        User retrievedUser = userService.getUserById(userId);

        assertNotNull(retrievedUser, "User should be retrievable by ID");
        assertEquals("Alice Cooper", retrievedUser.getName(), "User name should match the input name");
        assertEquals("securePass", retrievedUser.getPassword(), "Password should match the input password");
    }

    @Test
    public void testRegisterMembership() {
        // Verify membership registration functionality
        String userId = userService.createAccount("Bob Marley", "oneLovePass");
        userService.registerMembership(userId, Membership.GOLD);

        User user = userService.getUserById(userId);
        assertNotNull(user.getMembership(), "User should have a membership after registration");
        assertEquals(Membership.GOLD, user.getMembership(), "Membership type should be GOLD");
    }

    @Test
    public void testRegisterMembershipForNonExistentUser() {
        // Test registering a membership for a user ID that doesn’t exist
        userService.registerMembership("nonExistentId", Membership.SILVER);
        // Since there’s no user with this ID, no membership should be set; this test simply ensures no exceptions are thrown
        assertNull(userService.getUserById("nonExistentId"), "No user should exist for an invalid ID");
    }

    @Test
    public void testAccountPasswordHashing() {
        // Test to ensure passwords are stored as they are without hashing in this implementation
        String userId = userService.createAccount("Charlie Brown", "peanuts123");
        User user = userService.getUserById(userId);

        assertEquals("peanuts123", user.getPassword(), "Password should match input as no hashing is implemented");
    }
}
