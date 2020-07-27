package test.epam.bookstore.validator;

import by.epam.bookstore.exception.DaoException;
import by.epam.bookstore.model.dao.impl.BookDaoImpl;
import by.epam.bookstore.model.entity.Book;
import by.epam.bookstore.validator.BookValidator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class BookValidatorTest {

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
    public void testIsDuplicateBook() {
        Book book = new Book("In Search of Lost Time", 1913, 468, "Marcel Proust");
        boolean valid = BookValidator.isDuplicateBook(book, books);
        assertTrue(valid);
    }

    @Test(dataProvider = "falseTest")
    public void testFalseIsDuplicateBook(Book book) {
        boolean valid = BookValidator.isDuplicateBook(book, books);
        assertFalse(valid);
    }

    @Test
    public void testIsYear() {
        boolean value = BookValidator.isYear("1809");
        assertTrue(value);
    }

    @Test(dataProvider = "falseTestInt")
    public void testFalseIsYear(String year) {
        boolean value = BookValidator.isYear(year);
        assertFalse(value);
    }

    @Test
    public void testIsGoodString() {
        boolean value = BookValidator.isGoodString("Good String");
        assertTrue(value);
    }

    @Test
    public void testFalseIsGoodString() {
        boolean value = BookValidator.isGoodString(null);
        assertFalse(value);
    }

    @Test
    public void testFalseTwoIsGoodString() {
        boolean value = BookValidator.isGoodString(" ");
        assertFalse(value);
    }

    @Test
    public void testIsPositiveInteger() {
        boolean value = BookValidator.isPositiveInteger("4");
        assertTrue(value);
    }

    @Test(dataProvider = "falseTestInt")
    public void testFalseIsPositiveInteger(String id) {
        boolean value = BookValidator.isPositiveInteger(id);
        assertFalse(value);
    }

    @DataProvider(name = "falseTestInt")
    public Object[][] falseTestDataInt() {
        return new Object[][]{
                {""}, {"  "}, {null}, {"Not integer"}, {"40666677777645"}, {"-50"}
        };
    }

    @DataProvider(name = "falseTest")
    public Object[][] falseTestData() {
        Book bookOne = new Book("In Search of Lost Time", 1913, 468, "Author", "Author2");
        Book bookTwo = new Book("In Time", 1913, 468, "Marcel Proust");
        Book bookThree = new Book("In Search of Lost Time", 19, 468, "Marcel Proust");
        Book bookFour = new Book("In Search of Lost Time", 1913, 40000, "Marcel Proust");
        return new Object[][]{{bookOne}, {bookTwo}, {bookThree}, {bookFour}};
    }

}