package test.epam.bookstore.controller.command.impl;

import by.epam.bookstore.controller.command.impl.EmptyCommand;
import by.epam.bookstore.model.entity.Book;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class EmptyCommandTest {

    EmptyCommand command = new EmptyCommand();
    Map<String, String> requestParam = new HashMap<>();

    @Test
    public void testExecute() {
        Map<String, List<Book>> actual = command.execute(requestParam);
        Map<String, List<Book>> expected = new HashMap<>();
        List<Book> teResponse = new ArrayList<>();
        expected.put("bookList", teResponse);
        assertEquals(actual, expected);
    }
}