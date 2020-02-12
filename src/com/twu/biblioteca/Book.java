package com.twu.biblioteca;

public class Book implements LibraryItem {
    private String title;
    private String author;
    private int year;
    private String rating;
    private boolean checkedOut;
    private String borrower;

    public Book(String title, String author, int year, String rating) throws IllegalArgumentException{
        if(title.isEmpty()) {
            throw new IllegalArgumentException("Title can't be empty");
        }
        if(author.isEmpty()) {
            throw new IllegalArgumentException(("Author can't be empty"));
        }
        if(year < 1500 || year > 2020) {
            throw new IllegalArgumentException("Year has to be between 1500 and 2020");
        }
        if (rating.isEmpty()) {
            throw new IllegalArgumentException("Rating can't be empty");
        }
        this.title = title;
        this.author = author;
        this.year = year;
        this.rating = rating;
        this.checkedOut = false;
        this.borrower = "";
    }

    public void checkOut(String borrower) {
        checkedOut = true;
        this.borrower = borrower;
    }

    public void checkIn() {
        checkedOut = false;
        borrower = "";
    }

    public String getTitle() {
        return title;
    }

    public String getEditor() {
        return author;
    }

    public String getRating() {
        return rating;
    }

    public int getYear() {
        return year;
    }

    public boolean getCheckedOut() {
        return checkedOut;
    }

    public String getBorrower() {
        return borrower;
    }


}
