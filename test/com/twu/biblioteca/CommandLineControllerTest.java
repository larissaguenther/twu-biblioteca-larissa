package com.twu.biblioteca;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CommandLineControllerTest {

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


}