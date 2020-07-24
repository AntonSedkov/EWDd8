package by.epam.bookstore.model.dao;

import by.epam.bookstore.exception.DaoException;
import by.epam.bookstore.model.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BookDao extends AbstractDao<Integer, Book> {

    Optional<Book> findByID(int id) throws DaoException;

    List<Book> findAndSortByTitle(String... title) throws DaoException;

    List<Book> findAndSortByYearPublishing(int... yearPublishing) throws DaoException;

    List<Book> findAndSortByAuthor(String... author) throws DaoException;

    List<Book> findAndSortByPages(int... pages) throws DaoException;

    int countQuantityBooksInBookstore() throws DaoException;

    default void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                // TODO: 24.07.2020 log.error("ResultSet unable to close", e);
            }
        }
    }

}
