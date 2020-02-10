package com.twu.biblioteca;

import jdk.internal.util.xml.impl.Input;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.InputStream;
import java.io.ByteArrayInputStream;

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
        assertEquals("Menu\n" +
                "#To choose a menu option use the respective number of the menu option\n" +
                "1 List of Books\n", outputStream.toString());
    }

    @Test
    public void shouldGetInput() {
        //Given
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream menuStream = new PrintStream(outputStream);
        CommandLineInterface commandLineInterface = new CommandLineInterface(menuStream);
        String expectedInput = "List of Books";
        InputStream inputStream = new ByteArrayInputStream(expectedInput.getBytes());
        System.setIn(inputStream);
        //When
        String actualInput = commandLineInterface.getInput();
        //Then
        assertEquals(expectedInput, actualInput);
    }

}