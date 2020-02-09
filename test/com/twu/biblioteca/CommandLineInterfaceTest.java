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
    public void shouldDisplayMenu() {
        //Given
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream menuStream = new PrintStream(outputStream);
        CommandLineInterface commandLineInterface = new CommandLineInterface(menuStream);
        //When
        commandLineInterface.displayMenu();
        //Then
        assertEquals("List of Books\n", outputStream.toString());
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
        assertEquals("Moby Dick | Herman Melville | 1851\nRobinson Crusoe | Daniel Defoe | 1871\nPride and Prejudice | Jane Austen | 1813\n", outputStream.toString());
    }

}