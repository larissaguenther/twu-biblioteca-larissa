package com.twu.biblioteca;

public class Movie implements LibraryItem {
    private String title;
    private int year;
    private String director;
    private String rating;
    private boolean checkedOut;
    private String borrower;

    public Movie(String title, int year, String director, String rating) throws IllegalArgumentException {
        if(title.isEmpty()) {
            throw new IllegalArgumentException("Title can't be empty");
        }
        if(director.isEmpty()) {
            throw new IllegalArgumentException("Director can't be empty");
        }
        if (rating.isEmpty()) {
            throw new IllegalArgumentException("Rating can't be empty");
        }
        if (year < 1920 || year > 2020) {
            throw new IllegalArgumentException("Year has to be between 1920 and 2020");
        }
        this.title = title;
        this.year = year;
        this.director = director;
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
        return director;
    }

    public int getYear() {
        return year;
    }

    public String getRating() {
        return rating;
    }

    public boolean getCheckedOut() {

        return checkedOut;
    }

    public String getBorrower() {
        return borrower;
    }

}
