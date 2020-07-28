package test.epam.bookstore.model.dao.impl;

import by.epam.bookstore.exception.DaoException;
import by.epam.bookstore.model.dao.impl.BookDaoImpl;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class BookDaoImplTest {

    //all tests run onto base db.sql
    //other methods tests from service and command

    BookDaoImpl dao = BookDaoImpl.getInstance();

    @Test
    public void testCountQuantityBooksInBookstore() {
        try {
            int actual = dao.countQuantityBooksInBookstore();
            int expected = 16;
            assertEquals(actual, expected);
        } catch (DaoException e) {
            fail();
        }
    }

    @Test
    public void testGetMaxIndexInBookstore() {
        try {
            int actual = dao.getMaxIndexInBookstore();
            int expected = 16;
            assertEquals(actual, expected);
        } catch (DaoException e) {
            fail();
        }
    }
}