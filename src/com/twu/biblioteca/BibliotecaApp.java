package com.twu.biblioteca;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class BibliotecaApp {

    public static void main(String[] args) {

        PrintStream outputStream =  new PrintStream(new FileOutputStream(FileDescriptor.out));

        CommandLineController commandLineController = new CommandLineController(outputStream);
        commandLineController.startApplication();
    }
}
