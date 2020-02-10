package com.twu.biblioteca;

import org.junit.Test;
import org.junit.Rule;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class CommandLineControllerTest {

    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private PrintStream stream = new PrintStream(outputStream);

    /*@Test
    public void shouldNotifyUserWhenInvalidMenuOptionIsEntered() {
        //Given
        CommandLineController commandLineController = new CommandLineController(stream);
        //When
        commandLineController.routeInput("invalid");
        //Then
        assertEquals("Please select a valid option\n", outputStream.toString());
    }

    @Test
    public void shouldExitApplicationWhenQuitIsEntered() {
        //Given
        CommandLineController commandLineController = new CommandLineController(stream);
        //When
        commandLineController.routeInput("quit");
        //Then
        //???
    }*/

}