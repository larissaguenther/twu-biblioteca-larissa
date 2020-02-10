package com.twu.biblioteca;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class LibraryTest {

    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private PrintStream stream = new PrintStream(outputStream);
    private ArrayList<Book> bookList = new ArrayList<Book>();

    @Test
    public void shouldDisplayListOfBooks() {
        //Given
        Library library = new Library(stream);
        //When
        library.displayBookList(setUpTestBookList(bookList));
        //Then
        assertEquals("List of Books\n" +
                "#To check-out a book use checkout:<booktitle>. To return a book use return:<booktitle>.\n" +
                "Moby Dick | Herman Melville | 1851\n" +
                "Robinson Crusoe | Daniel Defoe | 1871\n" +
                "Pride and Prejudice | Jane Austen | 1813\n", outputStream.toString());
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenInputIsNotValid() {
        //Given
        Library library = new Library(stream);
        //When
        library.processBookListInput(setUpTestBookList(bookList), "test");
    }

    @Test
    public void shouldConvertInputToTitle() {
        //Given
        Library library = new Library(stream);
        //When
        String expected = library.convertInput("checkout:Moby Dick");
        //Then
        assertEquals("Moby Dick", expected);
    }

    @Test
    public void shouldCheckOutBookWhenTitleIsEntered() {
        //Given
        Library library = new Library(stream);
        //When
        library.checkOutBook(setUpTestBookList(bookList),"Moby Dick");
        //Then
        assertTrue(bookList.get(0).getCheckedOut());
    }

    @Test
    public void shouldNotDisplayCheckedOutBooksInListOfBooks() {
        //Given
        Library library = new Library(stream);
        checkOutBook(setUpTestBookList(bookList), "Moby Dick");
        //When
        library.displayBookList(bookList);
        //Then
        assertEquals("List of Books\n" +
                "#To check-out a book use checkout:<booktitle>. To return a book use return:<booktitle>.\n" +
                "Robinson Crusoe | Daniel Defoe | 1871\n" +
                "Pride and Prejudice | Jane Austen | 1813\n", outputStream.toString());
    }

    @Test
    public void shouldNotifyUserWhenBookIsSuccessfullyCheckedOut() {
        //Given
        Library library = new Library(stream);
        //When
        library.checkOutBook(setUpTestBookList(bookList), "Moby Dick");
        //Then
        assertEquals("Thank you! Enjoy the book\n", outputStream.toString());
    }

    @Test
    public void shouldNotifyUserWhenSelectedBookIsNotAvailable() {
        //Given
        Library library = new Library(stream);
        //When
        library.checkOutBook(setUpTestBookList(bookList), "Test");
        //Then
        assertEquals("Sorry that book is not available\n", outputStream.toString());
    }
    @Test
    public void shouldNotifyUserWhenSelectedBookIsAlreadyCheckedOut() {
        //Given
        Library library = new Library(stream);
        checkOutBook(setUpTestBookList(bookList), "Moby Dick");
        //When
        library.checkOutBook(bookList, "Moby Dick");
        //Then
        assertEquals("Sorry that book is not available\n", outputStream.toString());
    }

    @Test
    public void shouldReturnBookWhenTitleIsEntered() {
        //Given
        Library library = new Library(stream);
        checkOutBook(setUpTestBookList(bookList), "Moby Dick");
        //When
        library.returnBook(bookList,"Moby Dick");
        //Then
        assertFalse(bookList.get(0).getCheckedOut());
    }

    @Test
    public void shouldNotifyUserWhenBookIsSucessfullyReturned() {
        //Given
        Library library = new Library(stream);
        checkOutBook(setUpTestBookList(bookList), "Moby Dick");
        //When
        library.returnBook(bookList, "Moby Dick");
        //Then
        assertEquals("Thank you for returning the book\n", outputStream.toString());
    }

    @Test
    public void shouldNotifyUserWhenReturnedBookIsNotValid() {
        //Given
        Library library = new Library(stream);
        //When
        library.returnBook(setUpTestBookList(bookList), "Invalid");
        //Then
        assertEquals("That is not a valid book to return\n", outputStream.toString());
    }

    @Test
    public void shouldNotifyUserWhenReturnedBookIsNotCheckedOut() {
        //Given
        Library library = new Library(stream);
        //When
        library.returnBook(setUpTestBookList(bookList), "Moby Dick");
        //Then
        assertEquals("That book is already checked-in\n", outputStream.toString());
    }

    private ArrayList<Book> setUpTestBookList(ArrayList<Book> bookList) {
        Book book1 = new Book("Moby Dick", "Herman Melville", 1851);
        Book book2 = new Book("Robinson Crusoe", "Daniel Defoe", 1871);
        Book book3 = new Book("Pride and Prejudice", "Jane Austen", 1813);
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        return bookList;
    }

    private ArrayList<Book> checkOutBook(ArrayList<Book> bookList, String title) {
        Library library = new Library(stream);
        for(int i = 0; i < bookList.size(); i++) {
            if(bookList.get(i).getTitle().equals(title)){
                bookList.get(i).checkOut();
            }
        }
        return bookList;
    }
}