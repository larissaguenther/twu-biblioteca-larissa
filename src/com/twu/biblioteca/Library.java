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
            if (bookList.get(i).getCheckedOut() == false) {
                outputStream.println(bookList.get(i).getTitle() + " | " + bookList.get(i).getAuthor() + " | " + bookList.get(i).getYear());
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

    public void checkOutBook(ArrayList<Book> bookList, String title) {
        for(int i = 0; i < bookList.size(); i++) {
            if(bookList.get(i).getTitle().equals(title)) {
                bookList.get(i).checkOut();
            }
        }

    }
}
