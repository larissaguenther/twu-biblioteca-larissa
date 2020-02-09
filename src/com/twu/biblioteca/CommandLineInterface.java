package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.ArrayList;

public class CommandLineInterface {
    private PrintStream outputStream;

    public CommandLineInterface(PrintStream outputStream) {
        this.outputStream = outputStream;
    }

    public void displayWelcomeMessage() {
        outputStream.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }

    public void displayBookList() {
        ArrayList<Book> bookList = new ArrayList<Book>();
        setUpBookList(bookList);
        printBookList(bookList);
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

    private void printBookList(ArrayList<Book> bookList) {
        for(int i = 0; i < bookList.size(); i++) {
            outputStream.println(bookList.get(i).getTitle() + " | " + bookList.get(i).getAuthor() + " | " + bookList.get(i).getYear());
        }
    }
}
