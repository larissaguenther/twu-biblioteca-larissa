package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    private PrintStream outputStream;
    private ArrayList<LibraryItem> bookList;
    private ArrayList<LibraryItem> movieList;
    private CommandLineInterface commandLineInterface;

    public Library(PrintStream outputStream) {
        this.outputStream = outputStream;
        this.bookList = new ArrayList<LibraryItem>();
        this.movieList = new ArrayList<LibraryItem>();
        setUpBookList(bookList);
        setUpMovieList(movieList);
        this.commandLineInterface = new CommandLineInterface(outputStream);
    }

    public void chooseMenuOption(String input) {
        if (input.equals("1")) {
            chooseBookListOption();
        } else if (input.equals("2")) {
            chooseMovieListOption();
        } else if (input.equals("quit")) {
            chooseQuitOption();
        } else {
            chooseInvalidOption();
        }
    }

    private void chooseBookListOption() {
        displayList(bookList);
        while (true) {
            processListInput(bookList, commandLineInterface.getInput());
        }
    }

    private void chooseMovieListOption() {
        displayList(movieList);
        while(true) {
            processListInput(movieList, commandLineInterface.getInput());
        }
    }

    private void chooseQuitOption() {
        System.exit(0);
    }

    private void chooseInvalidOption() {
        commandLineInterface.printOutput("Please select a valid option");
    }

    public void displayList(ArrayList<LibraryItem> libraryItemList) {
        commandLineInterface.printOutput("#To check-out an item use 'checkout:<title>'. To return an item use 'return:<title>'");
        for(int i = 0; i < libraryItemList.size(); i++) {
            displayOnlyNonCheckedOutItems(libraryItemList.get(i));
        }
    }

    private void displayOnlyNonCheckedOutItems(LibraryItem libraryItem) {
        if (libraryItem.getCheckedOut() == false) {
            commandLineInterface.printOutput(libraryItem.getTitle());
        }
    }

    public void processListInput(ArrayList<LibraryItem> libraryItemList, String input) {
        if(input.startsWith("checkout")) {
            checkOutLibraryItem(libraryItemList, convertInputToTitle(input));
        } else if(input.startsWith("return")) {
            checkInLibraryItem(libraryItemList, convertInputToTitle(input));
        }
        else if (input.equals("quit")){
            System.exit(0);
        } else if(input.equals("menu")) {
            commandLineInterface.displayMenu();
            chooseMenuOption(commandLineInterface.getInput());
        }
        else {
            commandLineInterface.printOutput("Please select a valid option");
        }
    }

    public String convertInputToTitle(String input) {
        String[] inputArray = input.split(":");
        String title = inputArray[1];
        return title;
    }

    public void checkOutLibraryItem(ArrayList<LibraryItem> libraryItemList, String title) {
        boolean foundItem = false;
        for (int i = 0; i < libraryItemList.size(); i++) {
            if(libraryItemList.get(i).getTitle().equals(title)) {
                checkOutLibraryItemHelper(libraryItemList.get(i));
                foundItem = true;
            }
        }
        if(foundItem == false) {
            commandLineInterface.printOutput("Sorry that item is not available");
        }
    }

    public void checkOutLibraryItemHelper(LibraryItem libraryItem) {
        if(libraryItem.getCheckedOut() == false) {
            checkCredentials(libraryItem, getLibraryNumber(), getPassword());
        } else {
            commandLineInterface.printOutput("Sorry that item is not available");
        }
    }

    private String getLibraryNumber() {
        commandLineInterface.printOutput("Please enter your Librarynumber");
        String libraryNumber = commandLineInterface.getInput();
        return libraryNumber;
    }

    private String getPassword() {
        commandLineInterface.printOutput("Pleaser enter your Password");
        String password = commandLineInterface.getInput();
        return password;
    }

    public void checkCredentials(LibraryItem libraryItem, String libraryNumber, String password) {
        if(libraryNumber.equals("111-222") && password.equals("123")) {
            libraryItem.checkOut(libraryNumber);
            commandLineInterface.printOutput("Thank you! Enjoy");
        } else {
            commandLineInterface.printOutput("Sorry library number or password is not correct");
            //checkOutLibraryItemHelper(libraryItem);
        }
    }

    public void checkInLibraryItem(ArrayList<LibraryItem> libraryItemList, String title) {
        boolean foundItem = false;
        for (int i = 0; i < libraryItemList.size(); i++) {
            if(libraryItemList.get(i).getTitle().equals(title)) {
                checkInLibraryItemHelper(libraryItemList.get(i));
                foundItem = true;
            }
        }
        if (foundItem == false) {
            commandLineInterface.printOutput("That is not a valid item to return");
        }
    }

    private void checkInLibraryItemHelper(LibraryItem libraryItem) {
        if(libraryItem.getCheckedOut() == true) {
            libraryItem.checkIn();
            commandLineInterface.printOutput("Thank you for returning the item");
        } else {
            commandLineInterface.printOutput("That item is already checked-in");
        }
    }

    private ArrayList<LibraryItem> setUpBookList(ArrayList<LibraryItem> bookList) {
        Book book1 = new Book("Moby Dick", "Herman Melville", 1851);
        Book book2 = new Book("Robinson Crusoe", "Daniel Defoe", 1871);
        Book book3 = new Book("Pride and Prejudice", "Jane Austen", 1813);
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);

        return bookList;
    }

    private ArrayList<LibraryItem> setUpMovieList(ArrayList<LibraryItem> movieList) {
        Movie movie1 = new Movie("Pulp Fiction", 1993, "Quentin Tarantino", "8");
        Movie movie2 = new Movie("Kill Bill", 2001, "Quentin Tarantino", "9");
        Movie movie3 = new Movie("Inglorious Basterds", 2010, "Quentin Tarantino", "10");
        movieList.add(movie1);
        movieList.add(movie2);
        movieList.add(movie3);

        return movieList;
    }
}
