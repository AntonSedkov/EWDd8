package test.epam.bookstore.controller.command.impl;

import by.epam.bookstore.controller.command.impl.AddBookCommand;
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

public class AddBookCommandTest {

    AddBookCommand command;
    Map<String, String> requestParam;
    List<Book> books;

    @BeforeMethod
    public void setUp() {
        command = new AddBookCommand();
        requestParam = new HashMap<>();
        requestParam.put("title", "New Book");
        requestParam.put("year", "1800");
        requestParam.put("pages", "350");
        requestParam.put("authors", "AuthorOne, AuthorTwo");
    }

    @AfterMethod
    public void tearDown() {
        command = null;
        requestParam = null;
        books = null;
        BookService service = BookService.getInstance();
        try {
            int maxId = BookDaoImpl.getInstance().getMaxIndexInBookstore();
            service.removeBookById(String.valueOf(maxId));
        } catch (DaoException | BookServiceException e) {
            fail();
        }
    }

    @Test
    public void testExecute() {
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