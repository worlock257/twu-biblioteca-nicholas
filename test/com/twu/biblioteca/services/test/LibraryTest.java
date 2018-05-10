package com.twu.biblioteca.services.test;

import com.twu.biblioteca.models.Book;
import com.twu.biblioteca.models.Movie;
import com.twu.biblioteca.services.Library;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class LibraryTest {
    Library library;
    ArrayList<Book> bookList;
    ArrayList<Movie> filmList;
    Book book;


    @Before
    public void setUp(){

        library = new Library();
        bookList = new ArrayList<Book>();
        filmList = new ArrayList<Movie>();


        book = new Book("1", "TDD", "Kent", 2005);
        bookList.add(book);
        library.setBookList(bookList);

        filmList.add(new Movie("1", "The Matrix", "Nick", 2015));
        library.setFilmList(filmList);
    }


    @Test
    public void testShowManyBooks(){
        ArrayList<Book> _bookList = new ArrayList<Book>();

        _bookList.add(new Book("1","TDD","Kent", 2005));
        _bookList.add(new Book("2","Design Patterns","Fowler", 2004));
        library.setBookList(_bookList);

        assertEquals(String.format("%20s %20s %20s %20d %20s\n%20s %20s %20s %20d %20s", "1",
                "TDD", "Kent", 2005, "Free","2", "Design Patterns", "Fowler", 2004, "Free" ), library.getMediasAsString(_bookList));
    }


    @Test
    public void testGivenBookListShowMediaTable(){

        assertEquals(String.format("%20s %20s %20s %20s %20s\n%20s %20s %20s %20d %20s",
                "ID", "Name","Authors", "Years", "Status", "1", "TDD", "Kent", 2005, "Free"), library.showMediaInTable(bookList));
    }

    @Test
    public void testGivenFilmListShowMediaTable(){

        assertEquals(String.format("%20s %20s %20s %20s %20s\n%20s %20s %20s %20d %20s",
                "ID", "Name","Authors", "Years", "Status", "1", "The Matrix", "Nick", 2015, "Free"), library.showMediaInTable(filmList));
    }

    @Test
    public void testSucessfulBorrowBook(){

        String borrowMessage = library.borrowLibraryBook("1");

        assertEquals("Thank you! Enjoy the book", borrowMessage);
    }

    @Test
    public void testUnsuccessfulBorrowBook(){

        library.borrowLibraryBook("1");
        assertEquals("This book is not available", library.borrowLibraryBook("3"));
    }

    @Test
    public void testSuccessfulReturnBook(){

        library.borrowLibraryBook("1");
        assertEquals("Thank you for returning the book", library.returnBookToTheLibrary("1"));
    }

    @Test
    public void testUnsuccessfulReturnBook(){

        library.setBookList(bookList);
        library.borrowLibraryBook("1");
        library.returnBookToTheLibrary("1");

        assertEquals("This is not a valid book to return", library.returnBookToTheLibrary("3"));
        assertTrue(library.bookList.contains(book));
    }

    @Test
    public void testSucessfulBorrowFilm(){

        library.setFilmList(filmList);
        String borrowMessage = library.borrowLibraryFilm("1");

        assertEquals("Thank you! Enjoy the film", borrowMessage);
    }

    @Test
    public void testUnsuccessfulBorrowFilm(){

        library.setFilmList(filmList);
        library.borrowLibraryFilm("1");

        assertEquals("This film is not available", library.borrowLibraryFilm("2"));
    }

    @Test
    public void testSuccessfulReturnFilm(){

        library.setFilmList(filmList);
        library.borrowLibraryFilm("1");

        assertEquals("Thank you for returning the film", library.returnFilmToTheLibrary("1"));
    }

    @Test
    public void testUnsuccessfulReturnFilm(){

        library.setFilmList(filmList);
        library.borrowLibraryFilm("1");
        library.returnFilmToTheLibrary("1");

        assertEquals("This is not a valid film to return", library.returnFilmToTheLibrary("2"));
    }

}
