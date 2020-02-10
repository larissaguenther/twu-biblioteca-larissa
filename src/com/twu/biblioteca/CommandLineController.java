package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.ArrayList;

public class CommandLineController {
    private PrintStream outputStream;
    private CommandLineInterface commandLineInterface;
    private Library library;

    public CommandLineController(PrintStream outputStream) {
        this.outputStream = outputStream;
        this.library = new Library(outputStream);
        this.commandLineInterface = new CommandLineInterface(outputStream);
    }

    public void startApplication() {
        commandLineInterface.displayWelcomeMessage();
        commandLineInterface.displayMenu();
        commandLineInterface.processInput();
    }

    public void processMenuInput(String input) {
        if(input.equals("1")) {
            chooseMenuOption(input);
        } else if(input.equals("quit")) {
            System.exit(0);
        } else {
            showInvalidMenuOptionNotification();
        }
    }

    public void chooseMenuOption(String input) {
        if (input.equals("1")) {
            library.displayBookList(library.getBookList());
            while (true) {
                library.processBookListInput(library.getBookList(), commandLineInterface.getInput());
            }
        } else {

        }
    }

    public void showInvalidMenuOptionNotification() {
        outputStream.println("Please select a valid option");
    }
}
