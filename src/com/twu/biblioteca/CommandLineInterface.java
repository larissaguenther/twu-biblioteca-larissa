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
        Book book1 = new Book("Moby Dick");
        Book book2 = new Book("Robinson Crusoe");
        Book book3 = new Book("Pride and Prejudice");
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);

        for(int i = 0; i < bookList.size(); i++) {
            outputStream.println(bookList.get(i).getTitle());
        }
    }
}
