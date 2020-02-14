package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Library {
    private PrintStream outputStream;
    private ArrayList<LibraryItem> bookList;
    private ArrayList<LibraryItem> movieList;
    private HashMap<String, User> customers;
    private HashMap<String, User> librarians;
    private CommandLineInterface commandLineInterface;

    public Library(PrintStream outputStream) {
        this.outputStream = outputStream;
        this.bookList = new ArrayList<>();
        this.movieList = new ArrayList<>();
        this.customers = new HashMap<>();
        this.librarians = new HashMap<>();
        setUpBookList(bookList);
        setUpMovieList(movieList);
        setUpCustomers(customers);
        setUpLibrarians(librarians);
        this.commandLineInterface = new CommandLineInterface(outputStream);
    }

    public void chooseMenuOption(String input) {
        if (input.equals("1")) {
            chooseBookListOption();
        } else if (input.equals("2")) {
            chooseMovieListOption();
        } else if(input.equals("3")) {
            chooseUserLoginOption();
        } else if(input.equals("4")) {
            chooseLibrarianLoginOption();
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

    private void chooseUserLoginOption() {
        String libraryNumber = getLibraryNumber();
        String password = getPassword();
        if (checkCredentials(customers, libraryNumber, password) == true) {
            displayUserInformation(libraryNumber);

        } else {
            commandLineInterface.printOutput("Sorry wrong credentials");
        }
        while(true) {
            processLoginInput(commandLineInterface.getInput());
        }
    }

    private void chooseLibrarianLoginOption() {
        String libraryNumber = getLibraryNumber();
        String password = getPassword();
        if (checkCredentials(librarians, libraryNumber, password) == true) {
            displayInfoForLibrarians();
        } else {
            commandLineInterface.printOutput("Sorry, wrong credentials");
        }
        while(true) {
            processLoginInput(commandLineInterface.getInput());
        }
    }

    private void chooseQuitOption() {
        System.exit(0);
    }

    private void chooseInvalidOption() {
        commandLineInterface.printOutput("Please select a valid option");
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

    public void processLoginInput(String input) {
        if (input.equals("quit")){
            System.exit(0);
        } else if(input.equals("menu")) {
            commandLineInterface.displayMenu();
            chooseMenuOption(commandLineInterface.getInput());
        }
        else {
            commandLineInterface.printOutput("Please select a valid option");
        }
    }

    public void displayList(ArrayList<LibraryItem> libraryItemList) {
        commandLineInterface.printOutput("#To check-out an item use 'checkout:<title>'. To return an item use 'return:<title>'");
        for(int i = 0; i < libraryItemList.size(); i++) {
            displayOnlyNonCheckedOutItems(libraryItemList.get(i));
        }
    }

    private void displayOnlyNonCheckedOutItems(LibraryItem libraryItem) {
        if (libraryItem.getCheckedOut() == false) {
            commandLineInterface.printOutput(libraryItem.getTitle() +
                    " | " + libraryItem.getEditor() +
                    " | " + libraryItem.getYear() +
                    " | " + libraryItem.getRating());
        }
    }

    public void displayListForLibrarians(ArrayList<LibraryItem> libraryItemList) {
        for(int i = 0; i < libraryItemList.size(); i++) {
            displayAllItems(libraryItemList.get(i));
        }
    }

    private void displayAllItems(LibraryItem libraryItem) {
        if(libraryItem.getCheckedOut() == false) {
            commandLineInterface.printOutput("Checked-in: " + libraryItem.getTitle() +
                    " | " + libraryItem.getEditor() +
                    " | " + libraryItem.getYear() +
                    " | " + libraryItem.getRating());;
        } else {
            commandLineInterface.printOutput("Checked-out [" + libraryItem.getBorrower() + "]: " +
                    libraryItem.getTitle() +
                    " | " + libraryItem.getEditor() +
                    " | " + libraryItem.getYear() +
                    " | " + libraryItem.getRating());;
        }
    }


    public void displayUserInformation(String libraryNumber) {
        User user = customers.get(libraryNumber);
        commandLineInterface.printOutput("User information for Library Number " +
                libraryNumber);
        commandLineInterface.printOutput("Name: " + user.getName());
        commandLineInterface.printOutput("E-Mail: " + user.getEmail());
        commandLineInterface.printOutput("Phone Number: " + user.getNumber());
    }

    private void displayInfoForLibrarians() {
        commandLineInterface.printOutput("#List of Books");
        displayListForLibrarians(bookList);
        commandLineInterface.printOutput("#List of Movies");
        displayListForLibrarians(movieList);
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
                if(libraryItemList.get(i).getCheckedOut() == false) {
                    checkOutLibraryItemCheckCredentials(libraryItemList.get(i));
                } else {
                    commandLineInterface.printOutput("Sorry that item is not available");
                }
                foundItem = true;
            }
        }
        if(foundItem == false) {
            commandLineInterface.printOutput("Sorry that item is not available");
        }
    }

    private void checkOutLibraryItemCheckCredentials(LibraryItem libraryItem) {
        String libraryNumber = getLibraryNumber();
        String password = getPassword();
        if(checkCredentials(customers, libraryNumber, password) == true) {
            libraryItem.checkOut(libraryNumber);
            commandLineInterface.printOutput("Thank you! Enjoy");
        } else {
            commandLineInterface.printOutput("Sorry wrong credentials");
        }
    }

    public boolean checkCredentials(HashMap<String, User> users, String libraryNumber, String password) {
        boolean validCredentials = false;
        if(users.containsKey(libraryNumber)) {
            User user = users.get(libraryNumber);
            if (user.getPassword().equals(password)) {
                validCredentials = true;
            }
        }
        return validCredentials;
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
        Book book1 = new Book("Moby Dick", "Herman Melville", 1851, "7");
        Book book2 = new Book("Robinson Crusoe", "Daniel Defoe", 1871, "10");
        Book book3 = new Book("Pride and Prejudice", "Jane Austen", 1813, "unrated");
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

    private HashMap<String,User> setUpCustomers (HashMap<String, User> customers) {
        User customer1 = new User("123", "Mike Miller", "mike.miller@web.com", "017677334455");
        User customer2 = new User("345", "Mary Moon", "mary.moon@web.com", "017677334466");
        User customer3 = new User("567", "Sarah Smith", "sarah.smith@web.com", "017677334477");
        customers.put("111-222", customer1);
        customers.put("333-444", customer2);
        customers.put("555-666", customer3);

        return customers;
    }

    private HashMap<String,User> setUpLibrarians (HashMap<String, User> librarians) {
        User librarian1 = new User("ilovebooks", "Leo Librarian", "leo.librarian@web.com", "015664488");
        librarians.put("888-888", librarian1);

        return librarians;
    }
}
