package com.twu.biblioteca;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class CommandLineControllerTest {

    @Test
    public void shouldDisplayListOfBooksWhen1IsEntered() {
        //Given
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream bookListStream = new PrintStream(outputStream);
        CommandLineController commandLineController = new CommandLineController(bookListStream);
        //When
        commandLineController.chooseMenuOption("1");
        //Then
        assertEquals("Moby Dick | Herman Melville | 1851\nRobinson Crusoe | Daniel Defoe | 1871\nPride and Prejudice | Jane Austen | 1813\n", outputStream.toString());
    }

    @Test
    public void shouldNotifyUserWhenInvalidMenuOptionIsEntered() {
        //Given
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream errorStream = new PrintStream(outputStream);
        CommandLineController commandLineController = new CommandLineController(errorStream);
        //When
        commandLineController.chooseMenuOption("invalid");
        //Then
        assertEquals("Please select a valid option\n", outputStream.toString());

    }

    @Test
    public void shouldDisplayListOfBooks() {
        //Given
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream bookListStream = new PrintStream(outputStream);
        CommandLineController commandLineController = new CommandLineController(bookListStream);
        //When
        commandLineController.displayBookList();
        //Then
        assertEquals("Moby Dick | Herman Melville | 1851\nRobinson Crusoe | Daniel Defoe | 1871\nPride and Prejudice | Jane Austen | 1813\n", outputStream.toString());
    }
}