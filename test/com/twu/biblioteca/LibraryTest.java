package com.twu.biblioteca;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class LibraryTest {

    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private PrintStream stream = new PrintStream(outputStream);
    private ArrayList<LibraryItem> bookList = new ArrayList<LibraryItem>();
    private ArrayList<LibraryItem> movieList = new ArrayList<LibraryItem>();

    //---- test chooseMenuOption ----
    /*
    @Test
    public void shouldDisplayListOfBooksWhenMenuOption1IsChosen() {
        //Given
        Library library = new Library(stream);
        //When
        library.chooseMenuOption("1");
        //Then
        assertEquals("#To check-out an item use 'checkout:<title>. To return an item use 'return:<title>'\n" +
                "Moby Dick\n" +
                "Robinson Crusoe\n" +
                "Pride and Prejudice\n" ,outputStream.toString());
    }*/

    /*
    @Test
    public void shouldDisplayListOfMoviesWhenMenuOption2IsChosen() {
        //Given
        Library library = new Library(stream);
        //When
        library.chooseMenuOption("2");
        //Then
        assertEquals("#To check-out an item use 'checkout:<title>. To return an item use 'return:<title>'\n" +
                "Pulp Fiction\n" +
                "Kill Bill\n" +
                "Inglorious Basterds\n" ,outputStream.toString());
    }*/

    /*
    @Test
    public void shouldExitApplicationWhenMenuOptionQuitIsChosen() {
        //Given
        Library library = new Library(stream);
        //When
        library.chooseMenuOption("quit");
        //Then

    }*/

    @Test
    public void shouldNotifyUserWhenInvalidMenuOptionIsChosen() {
        //Given
        Library library = new Library(stream);
        //When
        library.chooseMenuOption("invalid");
        //Then
        assertEquals("Please select a valid option\n", outputStream.toString());
    }
    //---- test chooseMenuOption ----

    //---- test displayList ----
    @Test
    public void shouldDisplayListOfBooks() {
        //Given
        Library library = new Library(stream);
        //When
        library.displayList(setUpTestBookList(bookList));
        //Then
        assertEquals("#To check-out an item use 'checkout:<title>'. To return an item use 'return:<title>'\n" +
                "Moby Dick | Herman Melville | 1851 | 7\n" +
                "Robinson Crusoe | Daniel Defoe | 1871 | 10\n" +
                "Pride and Prejudice | Jane Austen | 1813 | unrated\n", outputStream.toString());
    }

    @Test
    public void shouldDisplayListOfMovies() {
        //Given
        Library library = new Library(stream);
        //When
        library.displayList(setUpTestMovieList(movieList));
        //Then
        assertEquals("#To check-out an item use 'checkout:<title>'. To return an item use 'return:<title>'\n" +
                "Pulp Fiction | Quentin Tarantino | 1993 | 8\n" +
                "Kill Bill | Quentin Tarantino | 2001 | 9\n" +
                "Inglorious Basterds | Quentin Tarantino | 2010 | 10\n", outputStream.toString());
    }

    @Test
    public void shouldOnlyDisplayNonCheckedOutItems() {
        //Given
        Library library = new Library(stream);
        checkOutBook(setUpTestBookList(bookList), "Moby Dick");
        //When
        library.displayList(bookList);
        //Then
        assertEquals("#To check-out an item use 'checkout:<title>'. To return an item use 'return:<title>'\n" +
                "Robinson Crusoe | Daniel Defoe | 1871 | 10\n" +
                "Pride and Prejudice | Jane Austen | 1813 | unrated\n", outputStream.toString());
    }
    //---- test displayList ----

    //---- test processListInput ----
    /*
    @Test
    public void shouldCheckOutItemWhenCheckOutListOptionIsChosen() {
        //Given
        Library library = new Library(stream);
        movieList = setUpTestMovieList(movieList);
        //When
        library.processListInput(movieList, "checkout:Pulp Fiction");
        //Then
        assertTrue(movieList.get(0).getCheckedOut());
    }*/

    @Test
    public void shouldCheckInItemWhenCheckInListOptionIsChosen() {
        //Given
        Library library = new Library(stream);
        bookList = checkOutBook(setUpTestBookList(bookList), "Moby Dick");
        //When
        library.processListInput(bookList, "return:Moby Dick");
        //Then
        assertFalse(bookList.get(0).getCheckedOut());
    }

    @Test
    public void shouldExitApplicationWhenQuitListOptionIsChosen() {
        //Given
        Library library = new Library(stream);
        //When
        library.processListInput(setUpTestBookList(bookList), "quit");
        //Then

    }

    /*
    @Test
    public void shouldDisplayMenuWhenMenuListOptionIsChosen() {
        //Given
        Library library = new Library(stream);
        //When
        library.processListInput(setUpTestMovieList(movieList), "menu");
        //Then
        assertEquals("#To choose a menu option use the respective number of the menu option\n" +
                "1 List of Books\n" +
                "2 List of Movies\n", outputStream.toString());
    }*/

    @Test
    public void shouldNotifyUserWhenInvalidListOptionIsChosen() {
        //Given
        Library library = new Library(stream);
        //When
        library.processListInput(setUpTestMovieList(movieList), "invalid");
        //Then
        assertEquals("Please select a valid option\n", outputStream.toString());
    }
    //---- test processListInput ----

    //---- test convertInputToTitle ----
    @Test
    public void shouldConvertInputToTitle() {
        //Given
        Library library = new Library(stream);
        //When
        String expected = library.convertInputToTitle("checkout:Moby Dick");
        //Then
        assertEquals("Moby Dick", expected);
    }
    //---- test convertInputToTitle ----

    //---- test checkOutLibraryItem ----
    @Test
    public void shouldNotifyUserWhenCheckedOutLibraryItemIsNotAvailable() {
        //Given
        Library library = new Library(stream);
        //When
        library.checkOutLibraryItem(setUpTestBookList(bookList), "Winnie Pooh");
        //Then
        assertEquals("Sorry that item is not available\n", outputStream.toString());
    }

    @Test
    public void shouldNotifyUserWhenCheckedOutLibraryItemIsAlreadyCheckedOut() {
        //Given
        Library library = new Library(stream);
        checkOutBook(setUpTestBookList(bookList), "Moby Dick");
        //When
        library.checkOutLibraryItem(bookList, "Moby Dick");
        //Then
        assertEquals("Sorry that item is not available\n", outputStream.toString());
    }
    
    //---- test checkOutLibraryItem ----
    @Test
    public void shouldCheckOutItemOnlyWhenCredentialsAreValid() {
        //Given
        Library library = new Library(stream);
        setUpTestMovieList(movieList);
        //When
        library.checkCredentials(movieList.get(0), "111-222", "123");
        //Then
        assertTrue(movieList.get(0).getCheckedOut());
    }

    @Test
    public void shouldNotifyUserWhenCredentialsAreInvalid() {
        //Given
        Library library = new Library(stream);
        setUpTestMovieList(movieList);
        //When
        library.checkCredentials(movieList.get(0), "111-222", "124");
        //Then
        assertEquals("Sorry library number or password is not correct\n", outputStream.toString());
    }
    //---- test checkOutLibraryItem ----

    //---- test checkInLibraryItem ----

    @Test
    public void shouldCheckInLibraryItem() {
        //Given
        Library library = new Library(stream);
        bookList = checkOutBook(setUpTestBookList(bookList), "Moby Dick");
        //When
        library.checkInLibraryItem(bookList, "Moby Dick");
        //Then
        assertFalse(bookList.get(0).getCheckedOut());
    }

    @Test
    public void shouldNotifyUserWhenCheckedInItemIsNotValid() {
        //Given
        Library library = new Library(stream);
        bookList = checkOutBook(setUpTestBookList(bookList), "Moby Dick");
        //When
        library.checkInLibraryItem(bookList, "Winnie Pooh");
        //Then
        assertEquals("That is not a valid item to return\n", outputStream.toString());
    }

    @Test
    public void shouldNotifyUserWhenCheckedInItemIsAlreadyCheckedIn() {
        //Given
        Library library = new Library(stream);
        //When
        library.checkInLibraryItem(setUpTestBookList(bookList), "Moby Dick");
        //Then
        assertEquals("That item is already checked-in\n", outputStream.toString());
    }

    private ArrayList<LibraryItem> setUpTestBookList(ArrayList<LibraryItem> bookList) {
        Book book1 = new Book("Moby Dick", "Herman Melville", 1851, "7");
        Book book2 = new Book("Robinson Crusoe", "Daniel Defoe", 1871, "10");
        Book book3 = new Book("Pride and Prejudice", "Jane Austen", 1813, "unrated");
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        return bookList;
    }

    private ArrayList<LibraryItem> setUpTestMovieList(ArrayList<LibraryItem> movieList) {
        Movie movie1 = new Movie("Pulp Fiction", 1993, "Quentin Tarantino", "8");
        Movie movie2 = new Movie("Kill Bill", 2001, "Quentin Tarantino", "9");
        Movie movie3 = new Movie("Inglorious Basterds", 2010, "Quentin Tarantino", "10");
        movieList.add(movie1);
        movieList.add(movie2);
        movieList.add(movie3);
        return movieList;
    }

    private ArrayList<LibraryItem> checkOutBook(ArrayList<LibraryItem> bookList, String title) {
        Library library = new Library(stream);
        for(int i = 0; i < bookList.size(); i++) {
            if(bookList.get(i).getTitle().equals(title)) {
                bookList.get(i).checkOut("111-222");
            }
        }
        return bookList;
    }
}