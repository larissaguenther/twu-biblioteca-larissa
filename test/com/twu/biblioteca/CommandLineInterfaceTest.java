package com.twu.biblioteca;

import jdk.internal.util.xml.impl.Input;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.InputStream;
import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

public class CommandLineInterfaceTest {

    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private PrintStream stream = new PrintStream(outputStream);

    @Test
    public void shouldDisplayWelcomeMessage() {
        //Given
        CommandLineInterface commandLineInterface = new CommandLineInterface(stream);
        //When
        commandLineInterface.displayWelcomeMessage();
        //Then
        assertEquals("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n", outputStream.toString());
    }

    @Test
    public void shouldDisplayMenu() {
        //Given
        CommandLineInterface commandLineInterface = new CommandLineInterface(stream);
        //When
        commandLineInterface.displayMenu();
        //Then
        assertEquals("Menu\n" +
                "#To choose a menu option use the respective number of the menu option\n" +
                "1 List of Books\n" +
                "2 List of Movies\n", outputStream.toString());
    }

    @Test
    public void shouldGetInput() {
        //Given
        CommandLineInterface commandLineInterface = new CommandLineInterface(stream);
        String expectedInput = "List of Books";
        InputStream inputStream = new ByteArrayInputStream(expectedInput.getBytes());
        System.setIn(inputStream);
        //When
        String actualInput = commandLineInterface.getInput();
        //Then
        assertEquals(expectedInput, actualInput);
    }

    @Test
    public void shouldPrintOutput() {
        //Given
        CommandLineInterface commandLineInterface = new CommandLineInterface(stream);
        //When
        commandLineInterface.printOutput("Test");
        //Then
        assertEquals("Test\n", outputStream.toString());
    }
}