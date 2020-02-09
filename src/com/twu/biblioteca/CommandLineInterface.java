package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class CommandLineInterface {
    private PrintStream outputStream;

    public CommandLineInterface(PrintStream outputStream) {
        this.outputStream = outputStream;
    }

    public void displayWelcomeMessage() {
        outputStream.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }

    public void displayMenu() {
        outputStream.println("1 List of Books");
    }

    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void processInput() {
        CommandLineController commandLineController = new CommandLineController(outputStream);
        commandLineController.chooseMenuOption(getInput());
    }
}
