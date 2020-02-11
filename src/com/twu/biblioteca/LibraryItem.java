package com.twu.biblioteca;

public interface LibraryItem {
    String getTitle();
    int getYear();
    boolean getCheckedOut();
    void checkOut(String borrower);
    void checkIn();
}
