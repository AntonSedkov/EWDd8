package by.epam.bookstore.model.dao.impl;

import by.epam.bookstore.exception.DaoException;
import by.epam.bookstore.model.connection.HelperDB;
import by.epam.bookstore.model.connection.StatementSql;
import by.epam.bookstore.model.dao.AbstractDao;
import by.epam.bookstore.model.entity.BookItem;
import by.epam.bookstore.model.type.DirectionType;
import by.epam.bookstore.model.type.SortType;
import by.epam.bookstore.parser.AuthorParser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao extends AbstractDao<Integer, BookItem> {

    private static final String COLUMN_ONE = "id_book";
    private static final String COLUMN_TWO = "title";
    private static final String COLUMN_THREE = "yearPublishing";
    private static final String COLUMN_FOUR = "pages";
    private static final String COLUMN_FIVE = "authors";
    private static final String ASC_SORT = "ASC";
    private static final String DESC_SORT = "DESC";

    @Override
    public List<BookItem> findAll() throws DaoException {
        List<BookItem> books = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        HelperDB helper = new HelperDB();
        try {
            preparedStatement = helper.getPreparedStatement(StatementSql.SQL_FIND_ALL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                BookItem book = new BookItem();
                book.setId(resultSet.getInt(COLUMN_ONE));
                book.setTitle(resultSet.getString(COLUMN_TWO));
                book.setYearPublishing(resultSet.getInt(COLUMN_THREE));
                book.setPages(resultSet.getInt(COLUMN_FOUR));
                String[] authors = AuthorParser.parseAuthors(resultSet.getString(COLUMN_FIVE));
                book.setAuthors(authors);
                books.add(book);
            }
        } catch (SQLException e) {
            throw new DaoException("SQL exception FindAll", e);
        } finally {
            helper.closeResultSet(resultSet);
            helper.closeStatement(preparedStatement);
            helper.closeConnection();
        }
        return books;
    }

    @Override
    public BookItem findByID(Integer id) throws DaoException {
        BookItem book;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        HelperDB helper = new HelperDB();
        try {
            preparedStatement = helper.getPreparedStatement(StatementSql.SQL_FIND_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            book = new BookItem();
            book.setId(id);
            book.setTitle(resultSet.getString(COLUMN_TWO));
            book.setYearPublishing(resultSet.getInt(COLUMN_THREE));
            book.setPages(resultSet.getInt(COLUMN_FOUR));
            String[] authors = AuthorParser.parseAuthors(resultSet.getString(COLUMN_FIVE));
            book.setAuthors(authors);
        } catch (SQLException e) {
            throw new DaoException("SQL exception FindAll", e);
        } finally {
            helper.closeResultSet(resultSet);
            helper.closeStatement(preparedStatement);
            helper.closeConnection();
        }
        return book;
    }

 /*   public List<BookItem> findAllWithSorting(SortType order) throws DaoException {
        String paramSort;
        switch (order) {
            case TITLE:
                paramSort = COLUMN_TWO;
                break;
            case AUTHOR:
                paramSort = COLUMN_FIVE;
                break;
            case YEAR_PUBLISHING:
                paramSort = COLUMN_THREE;
                break;
            case PAGES:
                paramSort = COLUMN_FOUR;
                break;
            default:
                paramSort = COLUMN_ONE;
        }
        String paramDirection;
     *//*   switch (direction) {
            case DESC:
                paramDirection = DESC_SORT;
                break;
            default:
                paramDirection = ASC_SORT;
        }*//*
        List<BookItem> books = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        HelperDB helper = new HelperDB();
        try {
            preparedStatement = helper.getPreparedStatement(StatementSql.SQL_FIND_ALL_SORT);
            preparedStatement.setString(1,paramSort);
           // preparedStatement.setString(2,paramDirection);
            System.out.println(preparedStatement);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                BookItem book = new BookItem();
                book.setId(resultSet.getInt("id_book"));
                book.setTitle(resultSet.getString("title"));
                book.setYearPublishing(resultSet.getInt("yearPublishing"));
                book.setPages(resultSet.getInt("pages"));
                String[] authors = AuthorParser.parseAuthors(resultSet.getString("authors"));
                book.setAuthors(authors);
                books.add(book);
            }
        } catch (SQLException e) {
            throw new DaoException("SQL exception FindAll", e);
        } finally {
            helper.closeResultSet(resultSet);
            helper.closeStatement(preparedStatement);
            helper.closeConnection();
        }
        return books;
    }
*/

    @Override
    public boolean create(BookItem entity) throws DaoException {
        return false;
    }

    @Override
    public BookItem update(BookItem entity) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(BookItem entity) throws DaoException {
        return false;
    }
}
