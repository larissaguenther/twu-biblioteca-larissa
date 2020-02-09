package com.twu.biblioteca;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class CommandLineInterfaceTest {

    @Test
    public void shouldDisplayWelcomeMessage() {
        //Given
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream welcomeStream = new PrintStream(outputStream);
        CommandLineInterface commandLineInterface = new CommandLineInterface(welcomeStream);
        //When
        commandLineInterface.displayWelcomeMessage();
        //Then
        assertEquals("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n", outputStream.toString());
    }

    @Test
    public void shouldDisplayListOfBooks() {
        //Given
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream bookListStream = new PrintStream(outputStream);
        CommandLineInterface commandLineInterface = new CommandLineInterface(bookListStream);
        //When
        commandLineInterface.displayBookList();
        //Then
        assertEquals("Moby Dick\nRobinson Crusoe\nPride and Prejudice\n", outputStream.toString());
    }

}