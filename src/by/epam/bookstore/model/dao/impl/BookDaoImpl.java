package by.epam.bookstore.model.dao.impl;

import by.epam.bookstore.exception.DaoException;
import by.epam.bookstore.model.connection.ConnectionPool;
import by.epam.bookstore.model.dao.BookDao;
import by.epam.bookstore.model.dao.ColumnName;
import by.epam.bookstore.model.dao.StatementSql;
import by.epam.bookstore.model.entity.Book;
import by.epam.bookstore.parser.AuthorParser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

public class BookDaoImpl implements BookDao {
    private static BookDaoImpl instance;
    private static ConnectionPool pool = ConnectionPool.INSTANCE;

    private BookDaoImpl() {
    }

    public static BookDaoImpl getInstance() {
        if (instance == null) {
            instance = new BookDaoImpl();
        }
        return instance;
    }


    @Override
    public boolean create(Book entity) throws DaoException {
        boolean result;
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(StatementSql.SQL_BOOK_ADD)) {
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setInt(2, entity.getYearPublishing());
            preparedStatement.setInt(3, entity.getPages());
            List<String> authors = new ArrayList<>(entity.getAuthors());
            StringJoiner authorsParam = new StringJoiner(", ");
            for (String author : authors) {
                authorsParam.add(author);
            }
            // TODO: 23.07.2020   System.out.println(authors);
            preparedStatement.setString(4, authorsParam.toString());
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Exception of adding book into database", e);
        }
        return result;
    }

    @Override
    public List<Book> findAll() throws DaoException {
        List<Book> books = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = pool.getConnection();
             Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery(StatementSql.SQL_BOOK_FIND_ALL);
            while (resultSet.next()) {
                Book book = createBookFromResultSet(resultSet);
                books.add(book);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception of getting all books from database", e);
        } finally {
            close(resultSet);
        }
        return books;
    }

    @Override
    public Optional<Book> findByID(int id) throws DaoException {
        Optional<Book> result = Optional.empty();
        ResultSet resultSet = null;
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(StatementSql.SQL_BOOK_FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = createBookFromResultSet(resultSet);
                result = Optional.of(book);
            }
            // TODO: 23.07.2020  System.out.println(result);
        } catch (SQLException e) {
            throw new DaoException("Exception of finding book by id from database", e);
        } finally {
            close(resultSet);
        }
        return result;
    }

    @Override
    public List<Book> findAndSortByTitle(String... title) throws DaoException {
        List<Book> books = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(StatementSql.SQL_BOOK_FIND_AND_SORT_BY_TITLE)) {
            if (title != null) {
                preparedStatement.setString(1, title[0]);
            }
            resultSet = preparedStatement.executeQuery();
            Book book = createBookFromResultSet(resultSet);
            books.add(book);
        } catch (SQLException e) {
            throw new DaoException("Exception of getting books by title from database", e);
        } finally {
            close(resultSet);
        }
        return books;
    }

    @Override
    public List<Book> findAndSortByYearPublishing(int... yearPublishing) throws DaoException {
        List<Book> books = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(StatementSql.SQL_BOOK_FIND_AND_SORT_BY_YEAR_PUBLISHING)) {
            if (yearPublishing != null) {
                preparedStatement.setInt(1, yearPublishing[0]);
            }
            resultSet = preparedStatement.executeQuery();
            Book book = createBookFromResultSet(resultSet);
            books.add(book);
        } catch (SQLException e) {
            throw new DaoException("Exception of getting books by yearPublishing from database", e);
        } finally {
            close(resultSet);
        }
        return books;
    }

    @Override
    public List<Book> findAndSortByAuthor(String... author) throws DaoException {
        List<Book> books = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(StatementSql.SQL_BOOK_FIND_AND_SORT_BY_AUTHOR)) {
            if (author != null) {
                preparedStatement.setString(1, author[0]);
            }
            resultSet = preparedStatement.executeQuery();
            Book book = createBookFromResultSet(resultSet);
            books.add(book);
        } catch (SQLException e) {
            throw new DaoException("Exception of getting books by author from database", e);
        } finally {
            close(resultSet);
        }
        return books;
    }

    @Override
    public List<Book> findAndSortByPages(int... pages) throws DaoException {
        List<Book> books = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(StatementSql.SQL_BOOK_FIND_AND_SORT_BY_PAGES)) {
            if (pages != null) {
                preparedStatement.setInt(1, pages[0]);
            }
            resultSet = preparedStatement.executeQuery();
            Book book = createBookFromResultSet(resultSet);
            books.add(book);
        } catch (SQLException e) {
            throw new DaoException("Exception of getting books by from database", e);
        } finally {
            close(resultSet);
        }
        return books;
    }

    @Override
    public boolean update(Book entity) throws DaoException {
        if (entity.getId() <= 0) {
            throw new DaoException("This book hasn't id and unable to update");
        }
        boolean result;
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(StatementSql.SQL_BOOK_UPDATE_BY_ID)) {
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setInt(2, entity.getYearPublishing());
            preparedStatement.setInt(3, entity.getPages());
            List<String> authors = new ArrayList<>(entity.getAuthors());
            StringJoiner authorsParam = new StringJoiner(", ");
            for (String author : authors) {
                authorsParam.add(author);
            }
            preparedStatement.setString(4, authorsParam.toString());
            preparedStatement.setInt(5, entity.getId());
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Exception of updating book into database", e);
        }
        return result;
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        boolean result;
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(StatementSql.SQL_BOOK_DELETE_BY_ID)) {
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Exception of deleting book from database", e);
        }
        return result;
    }

    @Override
    public boolean delete(Book entity) throws DaoException {
        boolean result;
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(StatementSql.SQL_BOOK_DELETE_WITHOUT_ID)) {
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setInt(2, entity.getYearPublishing());
            preparedStatement.setInt(3, entity.getPages());
            List<String> authors = new ArrayList<>(entity.getAuthors());
            StringJoiner authorsParam = new StringJoiner(", ");
            for (String author : authors) {
                authorsParam.add(author);
            }
            preparedStatement.setString(4, authorsParam.toString());
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Exception of deleting book from database", e);
        }
        return result;
    }

    @Override
    public int countQuantityBooksInBookstore() throws DaoException {
        ResultSet resultSet = null;
        int result = 0;
        try (Connection connection = pool.getConnection();
             Statement statement = connection.createStatement()) {
            // TODO: 24.07.2020 test this preparedStatement.executeUpdate();
            resultSet = statement.executeQuery(StatementSql.SQL_BOOK_COUNT_QUANTITY);
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception of counting books from database", e);
        } finally {
            close(resultSet);
        }
        return result;
    }

    private Book createBookFromResultSet(ResultSet resultSet) throws DaoException {
        Book book = new Book();
        try {
            book.setId(resultSet.getInt(ColumnName.COLUMN_BOOK_ID));
            book.setTitle(resultSet.getString(ColumnName.COLUMN_BOOK_TITLE));
            book.setYearPublishing(resultSet.getInt(ColumnName.COLUMN_BOOK_YEAR_PUBLISHING));
            book.setPages(resultSet.getInt(ColumnName.COLUMN_BOOK_PAGES));
            String[] authors = AuthorParser.parseAuthors(resultSet.getString(ColumnName.COLUMN_BOOK_AUTHORS));
            book.setAuthors(authors);
        } catch (SQLException e) {
            throw new DaoException("Exception of creating book from ResultSet", e);
        }
        return book;
    }

}