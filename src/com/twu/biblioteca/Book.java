package com.twu.biblioteca;

public class Book {
    private String title;

    public Book(String title) throws IllegalArgumentException{
        if(title.isEmpty()) {
            throw new IllegalArgumentException("Title can't be empty");
        }
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
