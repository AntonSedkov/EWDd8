package test.epam.bookstore.controller.command.impl;

import by.epam.bookstore.controller.command.impl.DeleteCommand;
import by.epam.bookstore.exception.BookCommandException;
import by.epam.bookstore.exception.BookServiceException;
import by.epam.bookstore.exception.DaoException;
import by.epam.bookstore.model.dao.impl.BookDaoImpl;
import by.epam.bookstore.model.entity.Book;
import by.epam.bookstore.model.service.BookService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class DeleteCommandTest {

    DeleteCommand command;
    Map<String, String> requestParam;
    List<Book> books;
    int maxId = 0;

    @BeforeMethod
    public void setUp() {
        command = new DeleteCommand();
        requestParam = new HashMap<>();
        BookService service = BookService.getInstance();
        try {
            service.addBook("Test Title", "1000", "100", "Author One, Author Two, Third Author");
            maxId = BookDaoImpl.getInstance().getMaxIndexInBookstore();
        } catch (DaoException | BookServiceException e) {
            fail();
        }
    }

    @AfterMethod
    public void tearDown() {
        command = null;
        requestParam = null;
        books = null;
    }

    @Test
    public void testExecuteById() {
        requestParam.put("id", String.valueOf(maxId));
        try {
            Map<String, List<Book>> actual = command.execute(requestParam);
            Map<String, List<Book>> expected = new HashMap<>();
            try {
                books = BookService.getInstance().getBooksFromBookStore();
            } catch (BookServiceException e) {
                fail();
            }
            List<Book> toResponse = new ArrayList<>(books);
            expected.put("bookList", toResponse);
            assertEquals(actual, expected);
        } catch (BookCommandException e) {
            fail();
        }
    }

    @Test
    public void testExecuteByParam() {
        requestParam.put("title", "Test Title");
        requestParam.put("year", "1000");
        requestParam.put("pages", "100");
        requestParam.put("authors", "Author One, Author Two, Third Author");
        try {
            Map<String, List<Book>> actual = command.execute(requestParam);
            Map<String, List<Book>> expected = new HashMap<>();
            try {
                books = BookService.getInstance().getBooksFromBookStore();
            } catch (BookServiceException e) {
                fail();
            }
            List<Book> toResponse = new ArrayList<>(books);
            expected.put("bookList", toResponse);
            assertEquals(actual, expected);
        } catch (BookCommandException e) {
            fail();
        }
    }

}