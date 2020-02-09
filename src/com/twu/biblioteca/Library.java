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

    public void checkOutBook(ArrayList<Book> bookList, String title) {
        for(int i = 0; i < bookList.size(); i++) {
            if(bookList.get(i).getTitle().equals(title)) {
                bookList.get(i).checkOut();
                outputStream.println("Thank you! Enjoy the book");
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
