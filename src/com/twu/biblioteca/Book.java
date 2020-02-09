package com.twu.biblioteca;

public class Book {
    private String title;
    private String author;
    private int year;
    private boolean checkedOut;

    public Book(String title, String author, int year) throws IllegalArgumentException{
        if(title.isEmpty()) {
            throw new IllegalArgumentException("Title can't be empty");
        }
        if(author.isEmpty()) {
            throw new IllegalArgumentException(("Author can't be empty"));
        }
        if(year < 1500 || year > 2020) {
            throw new IllegalArgumentException("Year has to be between 1500 and 2020");
        }
        this.title = title;
        this.author = author;
        this.year = year;
        this.checkedOut = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public boolean getCheckedOut() {
        return checkedOut;
    }

    public void checkOut() {
        this.checkedOut = true;
    }


}
