package by.epam.bookstore.model.dao;

import by.epam.bookstore.exception.DaoException;
import by.epam.bookstore.model.entity.BookItem;
import by.epam.bookstore.model.entity.EntityFlag;

import java.util.List;

public abstract class AbstractDao<K, T extends EntityFlag> {

    public abstract boolean create(T entity) throws DaoException;

    public abstract List<BookItem> findAll() throws DaoException;

    public abstract BookItem findByID(K id) throws DaoException;

    public abstract T update(T entity) throws DaoException;

    public abstract boolean delete(K id) throws DaoException;

    public abstract boolean delete(T entity) throws DaoException;

}
