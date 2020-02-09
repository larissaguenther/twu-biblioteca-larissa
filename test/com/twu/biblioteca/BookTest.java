package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.*;

public class BookTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenTitleIsEmpty() {
        Book book = new Book("");
    }
}