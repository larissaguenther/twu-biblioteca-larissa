package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    private PrintStream outputStream;
    private ArrayList<Book> bookList;
    private CommandLineInterface commandLineInterface;

    public Library(PrintStream outputStream) {
        this.outputStream = outputStream;
        this.bookList = new ArrayList<Book>();
        setUpBookList(bookList);
        this.commandLineInterface = new CommandLineInterface(outputStream);
    }

    public ArrayList<Book> getBookList() {
        return bookList;
    }

    public void chooseMenuOption(String input) {
        if (input.equals("1")) {
            chooseBookListOption();
        } else if (input.equals("quit")) {
            chooseQuitOption();
        } else {
            chooseInvalidOption();
        }
    }

    private void chooseBookListOption() {
        displayBookList(bookList);
        while (true) {
            processBookListInput(bookList, commandLineInterface.getInput());
        }
    }

    private void chooseQuitOption() {
        System.exit(0);
    }

    private void chooseInvalidOption() {
        commandLineInterface.printOutput("Please select a valid option");
    }

    public void displayBookList(ArrayList<Book> bookList) {
        commandLineInterface.printOutput("List of Books");
        commandLineInterface.printOutput("#To check-out a book use checkout:<booktitle>. To return a book use return:<booktitle>.");
        commandLineInterface.printOutput("#To return to menu use menu");
        for(int i = 0; i < bookList.size(); i++) {
            displayOnlyNonCheckedOutBooks(bookList.get(i));
        }
    }

    private void displayOnlyNonCheckedOutBooks(Book book) {
        if (book.getCheckedOut() == false) {
            commandLineInterface.printOutput(book.getTitle() + " | " + book.getAuthor() + " | " + book.getYear());
        }
    }

    public void processBookListInput(ArrayList<Book> bookList, String input) {
        if(input.startsWith("checkout")) {
            checkOutBook(bookList, convertInput(input));
        } else if(input.startsWith("return")) {
            returnBook(bookList, convertInput(input));
        } else if (input.equals("quit")){
            System.exit(0);
        } else if(input.equals("menu")) {
            commandLineInterface.displayMenu();
            chooseMenuOption(commandLineInterface.getInput());
        }
        else {
            commandLineInterface.printOutput("Please select a valid option");
        }
    }

    public String convertInput(String input) {
        String[] inputArray = input.split(":");
        String title = inputArray[1];
        return title;
    }

    public void checkOutBook(ArrayList<Book> bookList, String title) {
        boolean foundBook = false;
        for(int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getTitle().equals(title)) {
                checkOutBookHelper(bookList.get(i));
                foundBook = true;
            }
        }
        if(foundBook == false) {
                outputStream.println("Sorry that book is not available");
        }
    }

    private void checkOutBookHelper(Book book) {
        if (book.getCheckedOut() == false) {
            book.checkOut();
            commandLineInterface.printOutput("Thank you! Enjoy the book");
        } else {
            commandLineInterface.printOutput("Sorry that book is not available");
        }
    }

    public void returnBook(ArrayList<Book> bookList, String title) {
        boolean foundBook = false;
        for(int i = 0; i < bookList.size(); i++) {
            if(bookList.get(i).getTitle().equals(title)) {
                returnBookHelper(bookList.get(i));
                foundBook = true;
            }
        }
        if(foundBook == false) {
            outputStream.println("That is not a valid book to return");
        }
    }

    private void returnBookHelper(Book book) {
        if(book.getCheckedOut() == true) {
            book.checkIn();
            commandLineInterface.printOutput("Thank you for returning the book");
        } else {
            outputStream.println("That book is already checked-in");
        }
    }

    private ArrayList<Book> setUpBookList(ArrayList<Book> bookList) {
        Book book1 = new Book("Moby Dick", "Herman Melville", 1851);
        Book book2 = new Book("Robinson Crusoe", "Daniel Defoe", 1871);
        Book book3 = new Book("Pride and Prejudice", "Jane Austen", 1813);
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);

        return bookList;
    }
}
