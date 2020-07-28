package test.epam.bookstore.controller.command.impl;

import by.epam.bookstore.controller.command.impl.FindCommand;
import by.epam.bookstore.exception.BookCommandException;
import by.epam.bookstore.model.entity.Book;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class FindCommandTest {

    FindCommand command;
    Map<String, String> requestParam;

    @BeforeMethod
    public void setUp() {
        command = new FindCommand();
        requestParam = new HashMap<>();
    }

    @AfterMethod
    public void tearDown() {
        command = null;
        requestParam = null;
    }

    @Test
    public void testExecuteId() {
        requestParam.put("id", "1");
        try {
            Map<String, List<Book>> actual = command.execute(requestParam);
            Map<String, List<Book>> expected = new HashMap<>();
            List<Book> toResponse = new ArrayList<>();
            toResponse.add(new Book(1, "In Search of Lost Time", 1913, 468, "Marcel Proust"));
            expected.put("findList", toResponse);
            assertEquals(actual, expected);
        } catch (BookCommandException e) {
            fail();
        }
    }

    @Test
    public void testExecuteTitle() {
        requestParam.put("title", "Crime and Punishment");
        try {
            Map<String, List<Book>> actual = command.execute(requestParam);
            Map<String, List<Book>> expected = new HashMap<>();
            List<Book> toResponse = new ArrayList<>();
            toResponse.add(new Book(6, "Crime and Punishment", 1866, 430, "Fyodor Dostoyevsky"));
            expected.put("findList", toResponse);
            assertEquals(actual, expected);
        } catch (BookCommandException e) {
            fail();
        }
    }

    @Test
    public void testExecuteYear() {
        requestParam.put("year", "1472");
        try {
            Map<String, List<Book>> actual = command.execute(requestParam);
            Map<String, List<Book>> expected = new HashMap<>();
            List<Book> toResponse = new ArrayList<>();
            toResponse.add(new Book(7, "The Divine Comedy", 1472, 432, "Dante Alighieri"));
            expected.put("findList", toResponse);
            assertEquals(actual, expected);
        } catch (BookCommandException e) {
            fail();
        }
    }

    @Test
    public void testExecutePages() {
        requestParam.put("pages", "944");
        try {
            Map<String, List<Book>> actual = command.execute(requestParam);
            Map<String, List<Book>> expected = new HashMap<>();
            List<Book> toResponse = new ArrayList<>();
            toResponse.add(new Book(11, "The Talisman: A Novel", 2012, 944, "Stephen King", "Peter Straub"));
            expected.put("findList", toResponse);
            assertEquals(actual, expected);
        } catch (BookCommandException e) {
            fail();
        }
    }

    @Test
    public void testExecuteAuthor() {
        requestParam.put("author", "Neil Gaiman");
        try {
            Map<String, List<Book>> actual = command.execute(requestParam);
            Map<String, List<Book>> expected = new HashMap<>();
            List<Book> toResponse = new ArrayList<>();
            toResponse.add(new Book(9, "Good Omens", 2006, 512, "Neil Gaiman", "Terry Pratchett"));
            expected.put("findList", toResponse);
            assertEquals(actual, expected);
        } catch (BookCommandException e) {
            fail();
        }
    }

}