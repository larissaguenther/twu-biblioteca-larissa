package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    private PrintStream outputStream;
    private ArrayList<Book> bookList;

    public Library(PrintStream outputStream) {
        this.outputStream = outputStream;
        this.bookList = new ArrayList<Book>();
        setUpBookList(bookList);
    }

    public ArrayList<Book> getBookList() {
        return bookList;
    }

    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void displayBookList(ArrayList<Book> bookList) {
        for(int i = 0; i < bookList.size(); i++) {
           displayOnlyNonCheckedOutBooks(bookList.get(i));
        }
    }

    private void displayOnlyNonCheckedOutBooks(Book book) {
        if (book.getCheckedOut() == false) {
            outputStream.println(book.getTitle() + " | " + book.getAuthor() + " | " + book.getYear());
        }
    }

    public void processInput(ArrayList<Book> bookList, String input) throws IllegalArgumentException {
        if(input.startsWith("checkout")) {
            checkOutBook(bookList, getTitleFromInput(input));
        } else if(input.startsWith("return")) {
            returnBook(bookList, getTitleFromInput(input));
        } else if (input.equals("quit")){
            System.exit(0);
        } else {
            throw new IllegalArgumentException("This is not a valid command");
        }
    }

    public String getTitleFromInput(String input) {
        String[] inputArray = input.split(":");
        String title = inputArray[1];
        return title;
    }

    public void checkOutBook(ArrayList<Book> bookList, String title) {
        for(int i = 0; i < bookList.size(); i++) {
            if(bookList.get(i).getTitle().equals(title)) {
                if(bookList.get(i).getCheckedOut() == false) {
                    bookList.get(i).checkOut();
                    outputStream.println("Thank you! Enjoy the book");
                } else {
                    outputStream.println("Sorry that book is not available");
                }
            } else {
                //outputStream.println("Sorry that book is not available");
            }
        }
    }

    public void returnBook(ArrayList<Book> bookList, String title) {
        for(int i = 0; i < bookList.size(); i++) {
            if(bookList.get(i).getTitle().equals(title)) {
                if(bookList.get(i).getCheckedOut() == true) {
                    bookList.get(i).checkIn();
                } else {

                }
            } else {

            }
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
