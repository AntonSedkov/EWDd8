package test.epam.bookstore.controller;

import by.epam.bookstore.controller.ActionProvider;
import by.epam.bookstore.controller.command.Command;
import by.epam.bookstore.controller.command.impl.AddBookCommand;
import by.epam.bookstore.controller.command.impl.DeleteCommand;
import by.epam.bookstore.controller.command.impl.FindCommand;
import by.epam.bookstore.controller.command.impl.SortCommand;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ActionProviderTest {

    @Test
    public void testDefineCommandAdd() {
        Command actual = ActionProvider.defineCommand("ADD");
        Command expected = new AddBookCommand();
        boolean theSameClass = actual.getClass().equals(expected.getClass());
        assertTrue(theSameClass);
    }

    @Test
    public void testDefineCommandDelete() {
        Command actual = ActionProvider.defineCommand("DELETE");
        Command expected = new DeleteCommand();
        boolean theSameClass = actual.getClass().equals(expected.getClass());
        assertTrue(theSameClass);
    }

    @Test
    public void testDefineCommandFind() {
        Command actual = ActionProvider.defineCommand("FIND");
        Command expected = new FindCommand();
        boolean theSameClass = actual.getClass().equals(expected.getClass());
        assertTrue(theSameClass);
    }

    @Test
    public void testDefineCommandSort() {
        Command actual = ActionProvider.defineCommand("SORT");
        Command expected = new SortCommand();
        boolean theSameClass = actual.getClass().equals(expected.getClass());
        assertTrue(theSameClass);
    }

    @Test
    public void testDefineCommandWrong() {
        Command actual = ActionProvider.defineCommand("ADD");
        Command expected = new SortCommand();
        boolean theSameClass = actual.getClass().equals(expected.getClass());
        assertFalse(theSameClass);
    }

}