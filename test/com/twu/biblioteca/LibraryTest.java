package com.twu.biblioteca;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class LibraryTest {

    @Test
    public void shouldDisplayListOfBooks() {
        //Given
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream bookListStream = new PrintStream(outputStream);
        Library library = new Library(bookListStream);
        ArrayList<Book> bookList = new ArrayList<Book>();
        Book book1 = new Book("Moby Dick", "Herman Melville", 1851);
        Book book2 = new Book("Robinson Crusoe", "Daniel Defoe", 1871);
        Book book3 = new Book("Pride and Prejudice", "Jane Austen", 1813);
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        //When
        library.displayBookList(bookList);
        //Then
        assertEquals("Moby Dick | Herman Melville | 1851\nRobinson Crusoe | Daniel Defoe | 1871\nPride and Prejudice | Jane Austen | 1813\n", outputStream.toString());
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenInputIsNotValid() {
        //Given
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream bookListStream = new PrintStream(outputStream);
        Library library = new Library(bookListStream);
        ArrayList<Book> bookList = new ArrayList<Book>();
        Book book1 = new Book("Moby Dick", "Herman Melville", 1851);
        Book book2 = new Book("Robinson Crusoe", "Daniel Defoe", 1871);
        Book book3 = new Book("Pride and Prejudice", "Jane Austen", 1813);
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        //When
        library.processInput(bookList, "test");
    }

    @Test
    public void shouldGetTitleFromInput() {
        //Given
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream bookListStream = new PrintStream(outputStream);
        Library library = new Library(bookListStream);
        //When
        String expected = library.getTitleFromInput("checkout:Moby Dick");
        //Then
        assertEquals("Moby Dick", expected);
    }

    @Test
    public void shouldCheckOutBookWhenTitleIsEntered() {
        //Given
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream bookListStream = new PrintStream(outputStream);
        Library library = new Library(bookListStream);
        ArrayList<Book> bookList = new ArrayList<Book>();
        Book book = new Book("Moby Dick", "Herman Melville", 1851);
        bookList.add(book);
        //When
        library.checkOutBook(bookList,"Moby Dick");
        //Then
        assertTrue(bookList.get(0).getCheckedOut());
    }

    @Test
    public void shouldNotDisplayCheckedOutBooksInListOfBooks() {
        //Given
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream bookListStream = new PrintStream(outputStream);
        Library library = new Library(bookListStream);
        ArrayList<Book> bookList = new ArrayList<Book>();
        Book book1 = new Book("Moby Dick", "Herman Melville", 1851);
        Book book2 = new Book("Robinson Crusoe", "Daniel Defoe", 1871);
        Book book3 = new Book("Pride and Prejudice", "Jane Austen", 1813);
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        library.checkOutBook(bookList, "Moby Dick");
        //When
        library.displayBookList(bookList);
        //Then
        assertEquals("Thank you! Enjoy the book\nRobinson Crusoe | Daniel Defoe | 1871\nPride and Prejudice | Jane Austen | 1813\n", outputStream.toString());
    }

    @Test
    public void shouldNotifyUserWhenBookIsSuccessfullyCheckedOut() {
        //Given
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream bookListStream = new PrintStream(outputStream);
        Library library = new Library(bookListStream);
        ArrayList<Book> bookList = new ArrayList<Book>();
        Book book1 = new Book("Moby Dick", "Herman Melville", 1851);
        Book book2 = new Book("Robinson Crusoe", "Daniel Defoe", 1871);
        Book book3 = new Book("Pride and Prejudice", "Jane Austen", 1813);
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        //When
        library.checkOutBook(bookList, "Moby Dick");
        //Then
        assertEquals("Thank you! Enjoy the book\n", outputStream.toString());
    }

    @Test
    public void shouldNotifyUserWhenSelectedBookIsNotAvailable() {
        //Given
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream bookListStream = new PrintStream(outputStream);
        Library library = new Library(bookListStream);
        ArrayList<Book> bookList = new ArrayList<Book>();
        Book book1 = new Book("Moby Dick", "Herman Melville", 1851);
        Book book2 = new Book("Robinson Crusoe", "Daniel Defoe", 1871);
        Book book3 = new Book("Pride and Prejudice", "Jane Austen", 1813);
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        //When
        library.checkOutBook(bookList, "Test");
        //Then
        assertEquals("Sorry that book is not available\n", outputStream.toString());
    }
    @Test
    public void shouldNotifyUserWhenSelectedBookIsAlreadyCheckedOut() {
        //Given
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream bookListStream = new PrintStream(outputStream);
        Library library = new Library(bookListStream);
        ArrayList<Book> bookList = new ArrayList<Book>();
        Book book1 = new Book("Moby Dick", "Herman Melville", 1851);
        Book book2 = new Book("Robinson Crusoe", "Daniel Defoe", 1871);
        Book book3 = new Book("Pride and Prejudice", "Jane Austen", 1813);
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        library.checkOutBook(bookList,"Moby Dick");
        //When
        library.checkOutBook(bookList, "Moby Dick");
        //Then
        assertEquals("Thank you! Enjoy the book\nSorry that book is not available\n", outputStream.toString());
    }

    @Test
    public void shouldReturnBookWhenTitleIsEntered() {
        //Given
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream bookListStream = new PrintStream(outputStream);
        Library library = new Library(bookListStream);
        ArrayList<Book> bookList = new ArrayList<Book>();
        Book book = new Book("Moby Dick", "Herman Melville", 1851);
        bookList.add(book);
        library.checkOutBook(bookList, "Moby Dick");
        //When
        library.returnBook(bookList,"Moby Dick");
        //Then
        assertFalse(bookList.get(0).getCheckedOut());
    }

    @Test
    public void shouldNotifyUserWhenBookIsSucessfullyReturned() {
        //Given
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream bookListStream = new PrintStream(outputStream);
        Library library = new Library(bookListStream);
        ArrayList<Book> bookList = new ArrayList<Book>();
        Book book1 = new Book("Moby Dick", "Herman Melville", 1851);
        Book book2 = new Book("Robinson Crusoe", "Daniel Defoe", 1871);
        Book book3 = new Book("Pride and Prejudice", "Jane Austen", 1813);
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        library.checkOutBook(bookList, "Moby Dick");
        //When
        library.returnBook(bookList, "Moby Dick");
        //Then
        assertEquals("Thank you! Enjoy the book\nThank you for returning the book\n", outputStream.toString());
    }
}