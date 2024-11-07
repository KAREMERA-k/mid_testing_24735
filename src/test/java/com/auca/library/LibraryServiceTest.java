package com.auca.library;

import com.auca.library.model.User;
import com.auca.library.model.Location;
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
    public void testBorrowBookExceedsLimit() {
        String userId = userService.createAccount("John Doe", "password123");
        userService.registerMembership(userId, Membership.STRIVER);
        User user = userService.getUserById(userId);
        libraryService.borrowBook(user, "book001");
        libraryService.borrowBook(user, "book002");
        assertFalse(libraryService.borrowBook(user, "book003")); // Exceeds STRIVER limit
    }

    @Test
    public void testCalculateLateFee() {
        String userId = userService.createAccount("Jane Doe", "password321");
        userService.registerMembership(userId, Membership.GOLD);
        User user = userService.getUserById(userId);
        int fee = libraryService.calculateLateFee(user, 4); // 4 days late
        assertEquals(200, fee); // 4 days * 50 Rwf for GOLD
    }

    @Test
    public void testGetBookCountInRoom() {
        libraryService.assignBookToRoom("book001", 101);
        libraryService.assignBookToRoom("book002", 101);
        assertEquals(2, libraryService.getBookCountInRoom(101));
    }

    @Test
    public void testFindRoomWithFewestBooks() {
        libraryService.assignBookToRoom("book001", 101);
        libraryService.assignBookToRoom("book002", 101);
        libraryService.assignBookToRoom("book003", 102);
        assertEquals(102, libraryService.findRoomWithFewestBooks());
    }

    @Test
    public void testCalculateLateFeeForZeroDays() {
        String userId = userService.createAccount("Jane Doe", "password321");
        userService.registerMembership(userId, Membership.SILVER);
        User user = userService.getUserById(userId);
        int fee = libraryService.calculateLateFee(user, 0); // Zero days late
        assertEquals(0, fee);
    }

    @Test
    public void testBorrowWithoutMembership() {
        String userId = userService.createAccount("New User", "newpassword");
        User user = userService.getUserById(userId);

        assertThrows(IllegalStateException.class, () -> {
            libraryService.borrowBook(user, "book001"); // Should fail without membership
        });
    }

    @Test
    public void testAssignBookToShelf() {
        libraryService.assignBookToRoom("book004", 103);
        assertEquals(1, libraryService.getBookCountInRoom(103));
    }
}
