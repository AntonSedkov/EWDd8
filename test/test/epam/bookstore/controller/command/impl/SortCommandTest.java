package test.epam.bookstore.controller.command.impl;

import by.epam.bookstore.controller.command.impl.SortCommand;
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

public class SortCommandTest {

    SortCommand command;
    Map<String, String> requestParam;

    @BeforeMethod
    public void setUp() {
        command = new SortCommand();
        requestParam = new HashMap<>();
    }

    @AfterMethod
    public void tearDown() {
        command = null;
        requestParam = null;
    }

    @Test
    public void testExecuteId() {
        requestParam.put("sort_type", "id");
        try {
            Map<String, List<Book>> actual = command.execute(requestParam);
            Map<String, List<Book>> expected = new HashMap<>();
            List<Book> toResponse = new ArrayList<>();
            toResponse.add(new Book(1, "In Search of Lost Time", 1913, 468, "Marcel Proust"));
            toResponse.add(new Book(2, "Ulysses", 1922, 736, "James Joyce"));
            toResponse.add(new Book(3, "Don Quixote", 1615, 863, "Miguel de Cervantes"));
            toResponse.add(new Book(4, "The Catcher in the Rye", 1951, 234, "J. D. Salinger"));
            toResponse.add(new Book(5, "To Kill a Mockingbird", 1960, 281, "Harper Lee"));
            toResponse.add(new Book(6, "Crime and Punishment", 1866, 430, "Fyodor Dostoyevsky"));
            toResponse.add(new Book(7, "The Divine Comedy", 1472, 432, "Dante Alighieri"));
            toResponse.add(new Book(8, "Alice's Adventures in Wonderland", 1865, 201, "Lewis Carroll"));
            toResponse.add(new Book(9, "Good Omens", 2006, 512, "Neil Gaiman", "Terry Pratchett"));
            toResponse.add(new Book(10, "Tales from the Shadowhunter Academy", 2016, 672, "Cassandra Clare", "Sarah Rees Brennan", "Maureen Johnson", "Robin Wasserman"));
            toResponse.add(new Book(11, "The Talisman: A Novel", 2012, 944, "Stephen King", "Peter Straub"));
            toResponse.add(new Book(12, "Heads You Lose", 2012, 300, "Lisa Lutz", "David Hayward"));
            toResponse.add(new Book(13, "In Search of Lost Time", 20, 50, "A"));
            toResponse.add(new Book(14, "New Title", 1913, 51, "B"));
            toResponse.add(new Book(15, "New Title", 21, 468, "C"));
            toResponse.add(new Book(16, "New Title", 22, 53, "Marcel Proust", "Abc"));
            expected.put("sortedList", toResponse);
            assertEquals(actual, expected);
        } catch (BookCommandException e) {
            fail();
        }
    }

    @Test
    public void testExecuteTitle() {
        requestParam.put("sort_type", "TITLE");
        try {
            Map<String, List<Book>> actual = command.execute(requestParam);
            Map<String, List<Book>> expected = new HashMap<>();
            List<Book> toResponse = new ArrayList<>();
            toResponse.add(new Book(8, "Alice's Adventures in Wonderland", 1865, 201, "Lewis Carroll"));
            toResponse.add(new Book(6, "Crime and Punishment", 1866, 430, "Fyodor Dostoyevsky"));
            toResponse.add(new Book(3, "Don Quixote", 1615, 863, "Miguel de Cervantes"));
            toResponse.add(new Book(9, "Good Omens", 2006, 512, "Neil Gaiman", "Terry Pratchett"));
            toResponse.add(new Book(12, "Heads You Lose", 2012, 300, "Lisa Lutz", "David Hayward"));
            toResponse.add(new Book(1, "In Search of Lost Time", 1913, 468, "Marcel Proust"));
            toResponse.add(new Book(13, "In Search of Lost Time", 20, 50, "A"));
            toResponse.add(new Book(14, "New Title", 1913, 51, "B"));
            toResponse.add(new Book(15, "New Title", 21, 468, "C"));
            toResponse.add(new Book(16, "New Title", 22, 53, "Marcel Proust", "Abc"));
            toResponse.add(new Book(10, "Tales from the Shadowhunter Academy", 2016, 672, "Cassandra Clare", "Sarah Rees Brennan", "Maureen Johnson", "Robin Wasserman"));
            toResponse.add(new Book(4, "The Catcher in the Rye", 1951, 234, "J. D. Salinger"));
            toResponse.add(new Book(7, "The Divine Comedy", 1472, 432, "Dante Alighieri"));
            toResponse.add(new Book(11, "The Talisman: A Novel", 2012, 944, "Stephen King", "Peter Straub"));
            toResponse.add(new Book(5, "To Kill a Mockingbird", 1960, 281, "Harper Lee"));
            toResponse.add(new Book(2, "Ulysses", 1922, 736, "James Joyce"));
            expected.put("sortedList", toResponse);
            assertEquals(actual, expected);
        } catch (BookCommandException e) {
            fail();
        }
    }

    @Test
    public void testExecuteYear() {
        requestParam.put("sort_type", "year");
        try {
            Map<String, List<Book>> actual = command.execute(requestParam);
            Map<String, List<Book>> expected = new HashMap<>();
            List<Book> toResponse = new ArrayList<>();
            toResponse.add(new Book(13, "In Search of Lost Time", 20, 50, "A"));
            toResponse.add(new Book(15, "New Title", 21, 468, "C"));
            toResponse.add(new Book(16, "New Title", 22, 53, "Marcel Proust", "Abc"));
            toResponse.add(new Book(7, "The Divine Comedy", 1472, 432, "Dante Alighieri"));
            toResponse.add(new Book(3, "Don Quixote", 1615, 863, "Miguel de Cervantes"));
            toResponse.add(new Book(8, "Alice's Adventures in Wonderland", 1865, 201, "Lewis Carroll"));
            toResponse.add(new Book(6, "Crime and Punishment", 1866, 430, "Fyodor Dostoyevsky"));
            toResponse.add(new Book(1, "In Search of Lost Time", 1913, 468, "Marcel Proust"));
            toResponse.add(new Book(14, "New Title", 1913, 51, "B"));
            toResponse.add(new Book(2, "Ulysses", 1922, 736, "James Joyce"));
            toResponse.add(new Book(4, "The Catcher in the Rye", 1951, 234, "J. D. Salinger"));
            toResponse.add(new Book(5, "To Kill a Mockingbird", 1960, 281, "Harper Lee"));
            toResponse.add(new Book(9, "Good Omens", 2006, 512, "Neil Gaiman", "Terry Pratchett"));
            toResponse.add(new Book(11, "The Talisman: A Novel", 2012, 944, "Stephen King", "Peter Straub"));
            toResponse.add(new Book(12, "Heads You Lose", 2012, 300, "Lisa Lutz", "David Hayward"));
            toResponse.add(new Book(10, "Tales from the Shadowhunter Academy", 2016, 672, "Cassandra Clare", "Sarah Rees Brennan", "Maureen Johnson", "Robin Wasserman"));
            expected.put("sortedList", toResponse);
            assertEquals(actual, expected);
        } catch (BookCommandException e) {
            fail();
        }
    }

    @Test
    public void testExecutePages() {
        requestParam.put("sort_type", "Pages");
        try {
            Map<String, List<Book>> actual = command.execute(requestParam);
            Map<String, List<Book>> expected = new HashMap<>();
            List<Book> toResponse = new ArrayList<>();
            toResponse.add(new Book(13, "In Search of Lost Time", 20, 50, "A"));
            toResponse.add(new Book(14, "New Title", 1913, 51, "B"));
            toResponse.add(new Book(16, "New Title", 22, 53, "Marcel Proust", "Abc"));
            toResponse.add(new Book(8, "Alice's Adventures in Wonderland", 1865, 201, "Lewis Carroll"));
            toResponse.add(new Book(4, "The Catcher in the Rye", 1951, 234, "J. D. Salinger"));
            toResponse.add(new Book(5, "To Kill a Mockingbird", 1960, 281, "Harper Lee"));
            toResponse.add(new Book(12, "Heads You Lose", 2012, 300, "Lisa Lutz", "David Hayward"));
            toResponse.add(new Book(6, "Crime and Punishment", 1866, 430, "Fyodor Dostoyevsky"));
            toResponse.add(new Book(7, "The Divine Comedy", 1472, 432, "Dante Alighieri"));
            toResponse.add(new Book(1, "In Search of Lost Time", 1913, 468, "Marcel Proust"));
            toResponse.add(new Book(15, "New Title", 21, 468, "C"));
            toResponse.add(new Book(9, "Good Omens", 2006, 512, "Neil Gaiman", "Terry Pratchett"));
            toResponse.add(new Book(10, "Tales from the Shadowhunter Academy", 2016, 672, "Cassandra Clare", "Sarah Rees Brennan", "Maureen Johnson", "Robin Wasserman"));
            toResponse.add(new Book(2, "Ulysses", 1922, 736, "James Joyce"));
            toResponse.add(new Book(3, "Don Quixote", 1615, 863, "Miguel de Cervantes"));
            toResponse.add(new Book(11, "The Talisman: A Novel", 2012, 944, "Stephen King", "Peter Straub"));
            expected.put("sortedList", toResponse);
            assertEquals(actual, expected);
        } catch (BookCommandException e) {
            fail();
        }
    }

    @Test
    public void testExecuteAuthor() {
        requestParam.put("sort_type", "auTHOR");
        try {
            Map<String, List<Book>> actual = command.execute(requestParam);
            Map<String, List<Book>> expected = new HashMap<>();
            List<Book> toResponse = new ArrayList<>();
            toResponse.add(new Book(13, "In Search of Lost Time", 20, 50, "A"));
            toResponse.add(new Book(16, "New Title", 22, 53, "Marcel Proust", "Abc"));
            toResponse.add(new Book(14, "New Title", 1913, 51, "B"));
            toResponse.add(new Book(15, "New Title", 21, 468, "C"));
            toResponse.add(new Book(10, "Tales from the Shadowhunter Academy", 2016, 672, "Cassandra Clare", "Sarah Rees Brennan", "Maureen Johnson", "Robin Wasserman"));
            toResponse.add(new Book(7, "The Divine Comedy", 1472, 432, "Dante Alighieri"));
            toResponse.add(new Book(12, "Heads You Lose", 2012, 300, "Lisa Lutz", "David Hayward"));
            toResponse.add(new Book(6, "Crime and Punishment", 1866, 430, "Fyodor Dostoyevsky"));
            toResponse.add(new Book(5, "To Kill a Mockingbird", 1960, 281, "Harper Lee"));
            toResponse.add(new Book(4, "The Catcher in the Rye", 1951, 234, "J. D. Salinger"));
            toResponse.add(new Book(2, "Ulysses", 1922, 736, "James Joyce"));
            toResponse.add(new Book(8, "Alice's Adventures in Wonderland", 1865, 201, "Lewis Carroll"));
            toResponse.add(new Book(1, "In Search of Lost Time", 1913, 468, "Marcel Proust"));
            toResponse.add(new Book(3, "Don Quixote", 1615, 863, "Miguel de Cervantes"));
            toResponse.add(new Book(9, "Good Omens", 2006, 512, "Neil Gaiman", "Terry Pratchett"));
            toResponse.add(new Book(11, "The Talisman: A Novel", 2012, 944, "Stephen King", "Peter Straub"));
            expected.put("sortedList", toResponse);
            assertEquals(actual, expected);
        } catch (BookCommandException e) {
            fail();
        }
    }

}