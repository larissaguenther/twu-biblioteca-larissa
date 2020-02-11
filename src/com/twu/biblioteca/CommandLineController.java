package com.twu.biblioteca;

import java.io.PrintStream;

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
        while(true) {
            library.chooseMenuOption(commandLineInterface.getInput());
        }
    }
}
