package com.twu.biblioteca;

import java.io.PrintStream;
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
        outputStream.println("#To choose a menu option use the respective number of the menu option");
        outputStream.println("[1] List of Books");
        outputStream.println("[2] List of Movies");
        outputStream.println("[3] User Login");
        outputStream.println("[4] Librarian Login");
    }

    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void printOutput(String output) {
        outputStream.println(output);
    }
}
