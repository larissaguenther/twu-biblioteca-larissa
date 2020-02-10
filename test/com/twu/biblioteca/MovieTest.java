package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.*;

public class MovieTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenTitleIsEmpty() {
        Movie movie = new Movie("", 1993 , "Quentin Tarantino", "8");
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenYearHasWrongFormat() {
        Movie movie1 = new Movie("Pulp Fiction", 3 , "Quentin Tarantino", "8");
        Movie movie2 = new Movie("Pulp Fiction", 1900 , "Quentin Tarantino", "8");
        Movie movie3 = new Movie("Pulp Fiction", 2021 , "Quentin Tarantino", "8");
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenDirectorIsEmpty() {
        Movie movie = new Movie("Pulp Fiction", 1993 , "", "8");
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenRatingIsEmpty() {
        Movie movie = new Movie("Pulp Fiction", 1993, "Quentin Tarantino", "");
    }

}