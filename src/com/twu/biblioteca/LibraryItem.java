package com.twu.biblioteca;

public interface LibraryItem {
    String getTitle();
    String getEditor();
    int getYear();
    String getRating();
    boolean getCheckedOut();
    void checkOut(String borrower);
    void checkIn();
}
