package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.ArrayList;

public class CommandLineController {
    private PrintStream outputStream;
    private Library library;

    public CommandLineController(PrintStream outputStream) {
        this.outputStream = outputStream;
        this.library = new Library(outputStream);
    }

    public void chooseMenuOption(String input) {
        if (input.equals("1")) {
            library.displayBookList(library.getBookList());
            library.processInput(library.getBookList(), library.getInput());
        } else {
            outputStream.println("Please select a valid option");
        }
    }
}
