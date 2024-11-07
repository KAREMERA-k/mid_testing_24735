package com.auca.library.model;

public enum Membership {
    GOLD(5, 50),
    SILVER(3, 30),
    STRIVER(2, 10);

    private final int maxBooksAllowed;
    private final int dailyFee;

    Membership(int maxBooksAllowed, int dailyFee) {
        this.maxBooksAllowed = maxBooksAllowed;
        this.dailyFee = dailyFee;
    }

    public int getMaxBooksAllowed() {
        return maxBooksAllowed;
    }

    public int getDailyFee() {
        return dailyFee;
    }
}

