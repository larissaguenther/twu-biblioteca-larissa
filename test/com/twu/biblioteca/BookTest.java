package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.*;

public class BookTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenTitleIsEmpty() {
        Book book = new Book("", "Daniel Defoe" , 1871);
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenAuthorIsEmpty() {
        Book book = new Book("Robinson Crusoe", "", 1719);
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenYearHasWrongFormat() {
        Book book1 = new Book("Robinson Crusoe", "Daniel Defoe", 7);
        Book book2 = new Book("Robinson Crusoe", "Daniel Defoe", 89);
        Book book3 = new Book("Robinson Crusoe", "Daniel Defoe", 2023);
    }

    @Test
    public void shouldCheckOutBook() {
        //Given
        Book book = new Book("Robinson Crusoe", "Daniel Defoe", 1871);
        //When
        book.checkOut();
        //Then
        assertTrue(book.getCheckedOut());
    }

    @Test
    public void shouldReturnBook() {
        //Given
        Book book = new Book("Robinson Crusoe", "Daniel Defoe", 1871);
        book.checkOut();
        //When
        book.checkIn();
        //Then
        assertFalse(book.getCheckedOut());
    }
}