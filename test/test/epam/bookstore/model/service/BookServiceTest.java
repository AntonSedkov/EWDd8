package test.epam.bookstore.model.service;

import by.epam.bookstore.exception.BookServiceException;
import by.epam.bookstore.exception.DaoException;
import by.epam.bookstore.model.dao.impl.BookDaoImpl;
import by.epam.bookstore.model.entity.Book;
import by.epam.bookstore.model.service.BookService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.testng.Assert.*;

public class BookServiceTest {

    BookService service = BookService.getInstance();
    BookDaoImpl dao = BookDaoImpl.getInstance();
    List<Book> books;

    @BeforeMethod
    public void setUp() {
        try {
            books = dao.findAll();
        } catch (DaoException e) {
            fail();
        }
    }

    @AfterMethod
    public void tearDown() {
        books = null;
    }

    @Test
    public void testAddBook() {
        try {
            boolean actual = service.addBook("Test Example", "1", "1", "Author");
            assertTrue(actual);
        } catch (BookServiceException e) {
            fail();
        }
    }

    @Test(dependsOnMethods = {"testAddBook", "removeBookById"})
    public void testAddBookSecondRun() {
        try {
            boolean actual = service.addBook("Test Example", "1", "1", "Author");
            assertTrue(actual);
        } catch (BookServiceException e) {
            fail();
        }
    }

    @Test
    public void testDuplicateAddBook() {
        boolean actual = false;
        try {
            actual = service.addBook("In Search of Lost Time", "1913", "468", "Marcel Proust");
        } catch (BookServiceException e) {
            fail();
        }
        assertFalse(actual);
    }

    @Test(dependsOnMethods = {"testAddBook"})
    public void removeBookById() {
        try {
            int idMax = BookDaoImpl.getInstance().getMaxIndexInBookstore();
            boolean actual = service.removeBookById(String.valueOf(idMax));
            assertTrue(actual);
        } catch (BookServiceException | DaoException e) {
            fail();
        }
    }

    @Test(dependsOnMethods = {"testAddBook", "removeBookById", "testAddBookSecondRun"})
    public void removeBookWithoutId() {
        try {
            boolean actual = service.removeBookWithoutId("Test Example", "1", "1", "Author");
            assertTrue(actual);
        } catch (BookServiceException e) {
            fail();
        }
    }

    @Test
    public void testFalseRemoveBookById() {
        try {
            boolean actual = service.removeBookById("222222");
            assertFalse(actual);
        } catch (BookServiceException e) {
            fail();
        }
    }

    @Test
    public void testFalseRemoveBookWithoutId() {
        try {
            boolean actual = service.removeBookWithoutId("New Book", "2", "2", "Aut");
            assertFalse(actual);
        } catch (BookServiceException e) {
            fail();
        }
    }

    @Test
    public void testFindByID() {
        try {
            Optional<Book> actual = service.findByID("5");
            Optional<Book> expected = Optional.of(new Book(5, "To Kill a Mockingbird", 1960, 281, "Harper Lee"));
            assertEquals(actual, expected);
        } catch (BookServiceException e) {
            fail();
        }
    }

    @Test(expectedExceptions = BookServiceException.class, expectedExceptionsMessageRegExp = "Book ID is incorrect")
    public void testExceptionFindByID() throws BookServiceException {
        Optional<Book> actual = service.findByID("100100");
    }

    @Test
    public void testFindByTitle() {
        List<Book> actual = null;
        try {
            actual = service.findByTitle("In Search of Lost Time");
        } catch (BookServiceException e) {
            fail();
        }
        List<Book> expected = new ArrayList<>();
        expected.add(new Book(1, "In Search of Lost Time", 1913, 468, "Marcel Proust"));
        expected.add(new Book(13, "In Search of Lost Time", 20, 50, "A"));
        assertEquals(actual, expected);
    }

    @Test
    public void testFindByYearPublishing() {
        List<Book> actual = null;
        try {
            actual = service.findByYearPublishing("1913");
        } catch (BookServiceException e) {
            fail();
        }
        List<Book> expected = new ArrayList<>();
        expected.add(new Book(1, "In Search of Lost Time", 1913, 468, "Marcel Proust"));
        expected.add(new Book(14, "New Title", 1913, 51, "B"));
        assertEquals(actual, expected);
    }

    @Test
    public void testFindByAuthor() {
        List<Book> actual = null;
        try {
            actual = service.findByAuthor("Marcel Proust");
        } catch (BookServiceException e) {
            fail();
        }
        List<Book> expected = new ArrayList<>();
        expected.add(new Book(16, "New Title", 22, 53, "Marcel Proust", "Abc"));
        expected.add(new Book(1, "In Search of Lost Time", 1913, 468, "Marcel Proust"));
        assertEquals(actual, expected);
    }

    @Test
    public void testFindByPages() {
        List<Book> actual = null;
        try {
            actual = service.findByPages("468");
        } catch (BookServiceException e) {
            fail();
        }
        List<Book> expected = new ArrayList<>();
        expected.add(new Book(1, "In Search of Lost Time", 1913, 468, "Marcel Proust"));
        expected.add(new Book(15, "New Title", 21, 468, "C"));
        assertEquals(actual, expected);
    }

    @Test(dependsOnMethods = {"testAddBook", "removeBookById", "testAddBookSecondRun", "removeBookWithoutId"})
    public void testSortBooksByTitle() {
        List<Book> actual = null;
        try {
            actual = service.sortBooksByTitle();
        } catch (BookServiceException e) {
            fail();
        }
        List<Book> expected = new ArrayList<>();
        expected.add(new Book(8, "Alice's Adventures in Wonderland", 1865, 201, "Lewis Carroll"));
        expected.add(new Book(6, "Crime and Punishment", 1866, 430, "Fyodor Dostoyevsky"));
        expected.add(new Book(3, "Don Quixote", 1615, 863, "Miguel de Cervantes"));
        expected.add(new Book(9, "Good Omens", 2006, 512, "Neil Gaiman", "Terry Pratchett"));
        expected.add(new Book(12, "Heads You Lose", 2012, 300, "Lisa Lutz", "David Hayward"));
        expected.add(new Book(1, "In Search of Lost Time", 1913, 468, "Marcel Proust"));
        expected.add(new Book(13, "In Search of Lost Time", 20, 50, "A"));
        expected.add(new Book(14, "New Title", 1913, 51, "B"));
        expected.add(new Book(15, "New Title", 21, 468, "C"));
        expected.add(new Book(16, "New Title", 22, 53, "Marcel Proust", "Abc"));
        expected.add(new Book(10, "Tales from the Shadowhunter Academy", 2016, 672, "Cassandra Clare", "Sarah Rees Brennan", "Maureen Johnson", "Robin Wasserman"));
        expected.add(new Book(4, "The Catcher in the Rye", 1951, 234, "J. D. Salinger"));
        expected.add(new Book(7, "The Divine Comedy", 1472, 432, "Dante Alighieri"));
        expected.add(new Book(11, "The Talisman: A Novel", 2012, 944, "Stephen King", "Peter Straub"));
        expected.add(new Book(5, "To Kill a Mockingbird", 1960, 281, "Harper Lee"));
        expected.add(new Book(2, "Ulysses", 1922, 736, "James Joyce"));
        assertEquals(actual, expected);
    }

    @Test(dependsOnMethods = {"testAddBook", "removeBookById", "testAddBookSecondRun", "removeBookWithoutId"})
    public void testSortBooksByYearPublishing() {
        List<Book> actual = null;
        try {
            actual = service.sortBooksByYearPublishing();
        } catch (BookServiceException e) {
            fail();
        }
        List<Book> expected = new ArrayList<>();
        expected.add(new Book(13, "In Search of Lost Time", 20, 50, "A"));
        expected.add(new Book(15, "New Title", 21, 468, "C"));
        expected.add(new Book(16, "New Title", 22, 53, "Marcel Proust", "Abc"));
        expected.add(new Book(7, "The Divine Comedy", 1472, 432, "Dante Alighieri"));
        expected.add(new Book(3, "Don Quixote", 1615, 863, "Miguel de Cervantes"));
        expected.add(new Book(8, "Alice's Adventures in Wonderland", 1865, 201, "Lewis Carroll"));
        expected.add(new Book(6, "Crime and Punishment", 1866, 430, "Fyodor Dostoyevsky"));
        expected.add(new Book(1, "In Search of Lost Time", 1913, 468, "Marcel Proust"));
        expected.add(new Book(14, "New Title", 1913, 51, "B"));
        expected.add(new Book(2, "Ulysses", 1922, 736, "James Joyce"));
        expected.add(new Book(4, "The Catcher in the Rye", 1951, 234, "J. D. Salinger"));
        expected.add(new Book(5, "To Kill a Mockingbird", 1960, 281, "Harper Lee"));
        expected.add(new Book(9, "Good Omens", 2006, 512, "Neil Gaiman", "Terry Pratchett"));
        expected.add(new Book(11, "The Talisman: A Novel", 2012, 944, "Stephen King", "Peter Straub"));
        expected.add(new Book(12, "Heads You Lose", 2012, 300, "Lisa Lutz", "David Hayward"));
        expected.add(new Book(10, "Tales from the Shadowhunter Academy", 2016, 672, "Cassandra Clare", "Sarah Rees Brennan", "Maureen Johnson", "Robin Wasserman"));
        assertEquals(actual, expected);
    }

    @Test(dependsOnMethods = {"testAddBook", "removeBookById", "testAddBookSecondRun", "removeBookWithoutId"})
    public void testSortBooksByAuthors() {
        List<Book> actual = null;
        try {
            actual = service.sortBooksByAuthors();
        } catch (BookServiceException e) {
            fail();
        }
        List<Book> expected = new ArrayList<>();
        expected.add(new Book(13, "In Search of Lost Time", 20, 50, "A"));
        expected.add(new Book(16, "New Title", 22, 53, "Marcel Proust", "Abc"));
        expected.add(new Book(14, "New Title", 1913, 51, "B"));
        expected.add(new Book(15, "New Title", 21, 468, "C"));
        expected.add(new Book(10, "Tales from the Shadowhunter Academy", 2016, 672, "Cassandra Clare", "Sarah Rees Brennan", "Maureen Johnson", "Robin Wasserman"));
        expected.add(new Book(7, "The Divine Comedy", 1472, 432, "Dante Alighieri"));
        expected.add(new Book(12, "Heads You Lose", 2012, 300, "Lisa Lutz", "David Hayward"));
        expected.add(new Book(6, "Crime and Punishment", 1866, 430, "Fyodor Dostoyevsky"));
        expected.add(new Book(5, "To Kill a Mockingbird", 1960, 281, "Harper Lee"));
        expected.add(new Book(4, "The Catcher in the Rye", 1951, 234, "J. D. Salinger"));
        expected.add(new Book(2, "Ulysses", 1922, 736, "James Joyce"));
        expected.add(new Book(8, "Alice's Adventures in Wonderland", 1865, 201, "Lewis Carroll"));
        expected.add(new Book(1, "In Search of Lost Time", 1913, 468, "Marcel Proust"));
        expected.add(new Book(3, "Don Quixote", 1615, 863, "Miguel de Cervantes"));
        expected.add(new Book(9, "Good Omens", 2006, 512, "Neil Gaiman", "Terry Pratchett"));
        expected.add(new Book(11, "The Talisman: A Novel", 2012, 944, "Stephen King", "Peter Straub"));
        assertEquals(actual, expected);
    }

    @Test(dependsOnMethods = {"testAddBook", "removeBookById", "testAddBookSecondRun", "removeBookWithoutId"})
    public void testSortBooksByPages() {
        List<Book> actual = null;
        try {
            actual = service.sortBooksByPages();
        } catch (BookServiceException e) {
            fail();
        }
        List<Book> expected = new ArrayList<>();
        expected.add(new Book(13, "In Search of Lost Time", 20, 50, "A"));
        expected.add(new Book(14, "New Title", 1913, 51, "B"));
        expected.add(new Book(16, "New Title", 22, 53, "Marcel Proust", "Abc"));
        expected.add(new Book(8, "Alice's Adventures in Wonderland", 1865, 201, "Lewis Carroll"));
        expected.add(new Book(4, "The Catcher in the Rye", 1951, 234, "J. D. Salinger"));
        expected.add(new Book(5, "To Kill a Mockingbird", 1960, 281, "Harper Lee"));
        expected.add(new Book(12, "Heads You Lose", 2012, 300, "Lisa Lutz", "David Hayward"));
        expected.add(new Book(6, "Crime and Punishment", 1866, 430, "Fyodor Dostoyevsky"));
        expected.add(new Book(7, "The Divine Comedy", 1472, 432, "Dante Alighieri"));
        expected.add(new Book(1, "In Search of Lost Time", 1913, 468, "Marcel Proust"));
        expected.add(new Book(15, "New Title", 21, 468, "C"));
        expected.add(new Book(9, "Good Omens", 2006, 512, "Neil Gaiman", "Terry Pratchett"));
        expected.add(new Book(10, "Tales from the Shadowhunter Academy", 2016, 672, "Cassandra Clare", "Sarah Rees Brennan", "Maureen Johnson", "Robin Wasserman"));
        expected.add(new Book(2, "Ulysses", 1922, 736, "James Joyce"));
        expected.add(new Book(3, "Don Quixote", 1615, 863, "Miguel de Cervantes"));
        expected.add(new Book(11, "The Talisman: A Novel", 2012, 944, "Stephen King", "Peter Straub"));
        assertEquals(actual, expected);
    }

    @Test(dependsOnMethods = {"testAddBook", "removeBookById", "testAddBookSecondRun", "removeBookWithoutId"})
    public void testGetBooksFromBookStore() {
        List<Book> expected = new ArrayList<>();
        expected.add(new Book(1, "In Search of Lost Time", 1913, 468, "Marcel Proust"));
        expected.add(new Book(2, "Ulysses", 1922, 736, "James Joyce"));
        expected.add(new Book(3, "Don Quixote", 1615, 863, "Miguel de Cervantes"));
        expected.add(new Book(4, "The Catcher in the Rye", 1951, 234, "J. D. Salinger"));
        expected.add(new Book(5, "To Kill a Mockingbird", 1960, 281, "Harper Lee"));
        expected.add(new Book(6, "Crime and Punishment", 1866, 430, "Fyodor Dostoyevsky"));
        expected.add(new Book(7, "The Divine Comedy", 1472, 432, "Dante Alighieri"));
        expected.add(new Book(8, "Alice's Adventures in Wonderland", 1865, 201, "Lewis Carroll"));
        expected.add(new Book(9, "Good Omens", 2006, 512, "Neil Gaiman", "Terry Pratchett"));
        expected.add(new Book(10, "Tales from the Shadowhunter Academy", 2016, 672, "Cassandra Clare", "Sarah Rees Brennan", "Maureen Johnson", "Robin Wasserman"));
        expected.add(new Book(11, "The Talisman: A Novel", 2012, 944, "Stephen King", "Peter Straub"));
        expected.add(new Book(12, "Heads You Lose", 2012, 300, "Lisa Lutz", "David Hayward"));
        expected.add(new Book(13, "In Search of Lost Time", 20, 50, "A"));
        expected.add(new Book(14, "New Title", 1913, 51, "B"));
        expected.add(new Book(15, "New Title", 21, 468, "C"));
        expected.add(new Book(16, "New Title", 22, 53, "Marcel Proust", "Abc"));
        assertEquals(books, expected);
    }

}