package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenPasswordIsEmpty() {
        User user = new User("", "Name", "email", "phone");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenNameIsEmpty() {
        User user = new User("password", "", "email", "phone");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenEmailIsEmpty() {
        User user = new User("password", "Name", "", "phone");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenNumberIsEmpty() {
        User user = new User("password", "Name", "email", "");
    }
}