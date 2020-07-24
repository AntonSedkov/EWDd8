package by.epam.bookstore.model.dao;

import by.epam.bookstore.exception.DaoException;
import by.epam.bookstore.model.entity.Book;
import by.epam.bookstore.model.entity.Entity;

import java.util.List;

public interface AbstractDao<K, T extends Entity> {

    boolean create(T entity) throws DaoException;

    List<Book> findAll() throws DaoException;

    boolean update(T entity) throws DaoException;

    boolean delete(K id) throws DaoException;

    boolean delete(T entity) throws DaoException;

}
