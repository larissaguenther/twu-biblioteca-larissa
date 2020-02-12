package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.*;

public class BookTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenTitleIsEmpty() {
        Book book = new Book("", "Daniel Defoe" , 1871, "10");
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenAuthorIsEmpty() {
        Book book = new Book("Robinson Crusoe", "", 1719, "10");
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenYearHasWrongFormat() {
        Book book1 = new Book("Robinson Crusoe", "Daniel Defoe", 7, "10");
        Book book2 = new Book("Robinson Crusoe", "Daniel Defoe", 89, "10");
        Book book3 = new Book("Robinson Crusoe", "Daniel Defoe", 2023, "10");
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenRatingIsEmpty() {
        Book book = new Book("Robinson Crusoe", "Daniel Defoe", 1719, "");
    }


    @Test
    public void shouldCheckOutBook() {
        //Given
        Book book = new Book("Robinson Crusoe", "Daniel Defoe", 1871, "10");
        //When
        book.checkOut("111-222");
        //Then
        assertTrue(book.getCheckedOut());
    }

    @Test
    public void shouldReturnBook() {
        //Given
        Book book = new Book("Robinson Crusoe", "Daniel Defoe", 1871, "10");
        book.checkOut("111-222");
        //When
        book.checkIn();
        //Then
        assertFalse(book.getCheckedOut());
    }
}