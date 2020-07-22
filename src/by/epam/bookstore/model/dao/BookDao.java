package by.epam.bookstore.model.dao;

import by.epam.bookstore.exception.DaoException;
import by.epam.bookstore.model.entity.BookItem;

import java.util.List;
import java.util.Optional;

public interface BookDao {





    List<BookItem> findByYearPublishing(int yearPublishing) throws DaoException;

    List<BookItem> findByAuthor(String author) throws DaoException;

    List<BookItem> findByPages(int pages) throws DaoException;

    List<BookItem> sortBooksByID() throws DaoException;

    List<BookItem> sortBooksByTitle() throws DaoException;

    List<BookItem> sortBooksByYearPublishing() throws DaoException;

    List<BookItem> sortBooksByAuthors() throws DaoException;

    List<BookItem> sortBooksByPages() throws DaoException;

}
