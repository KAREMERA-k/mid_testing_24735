package com.auca.library;

import com.auca.library.model.User;
import com.auca.library.model.Membership;
import com.auca.library.service.LibraryService;
import com.auca.library.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryServiceTest {
    private LibraryService libraryService;
    private UserService userService;

    @BeforeEach
    public void setUp() {
        libraryService = new LibraryService();
        userService = new UserService();
    }

    @Test
    public void testBorrowBookWithinLimit() {
        String userId = userService.createAccount("John Doe", "password123");
        userService.registerMembership(userId, Membership.SILVER);
        User user = userService.getUserById(userId);
        assertTrue(libraryService.borrowBook(user, "book001"));
    }

    @Test
    public void testCalculateLateFee() {
        String userId = userService.createAccount("Jane Doe", "password321");
        userService.registerMembership(userId, Membership.GOLD);
        User user = userService.getUserById(userId);
        int fee = libraryService.calculateLateFee(user, 4);
        assertEquals(200, fee);
    }
}

