package com.auca.library.service;

import com.auca.library.model.User;

import java.util.HashMap;
import java.util.Map;

public class LibraryService {
    private Map<String, Integer> books = new HashMap<>(); // Book ID mapped to room number
    private Map<Integer, Integer> roomBookCount = new HashMap<>(); // Room number to book count

    public boolean borrowBook(User user, String bookId) {
        if (user.getMembership() == null) {
            throw new IllegalStateException("User must have a membership to borrow books.");
        }
        if (user.getBorrowedBooks().size() >= user.getMembership().getMaxBooksAllowed()) {
            return false; // Borrowing limit exceeded
        }
        user.getBorrowedBooks().put(bookId, 0); // Add book with initial borrow date
        return true;
    }

    public void assignBookToRoom(String bookId, int roomNumber) {
        books.put(bookId, roomNumber);
        roomBookCount.put(roomNumber, roomBookCount.getOrDefault(roomNumber, 0) + 1);
    }

    public int calculateLateFee(User user, int daysLate) {
        if (daysLate <= 0) return 0;
        return user.getMembership().getDailyFee() * daysLate;
    }

    public int getBookCountInRoom(int roomNumber) {
        return roomBookCount.getOrDefault(roomNumber, 0);
    }

    public int findRoomWithFewestBooks() {
        return roomBookCount.entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(-1); // Returns -1 if no rooms
    }
}

