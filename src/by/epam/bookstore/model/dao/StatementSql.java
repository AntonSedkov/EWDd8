package by.epam.bookstore.model.dao;

public class StatementSql {

    public static final String SQL_BOOK_ADD = "INSERT INTO books (title, yearPublishing, pages, authors) VALUES (?, ?, ?, ?)";
    public static final String SQL_BOOK_FIND_ALL = "SELECT id_book, title, yearPublishing, pages, authors FROM books";
    public static final String SQL_BOOK_FIND_BY_ID = "SELECT id_book, title, yearPublishing, pages, authors FROM books WHERE id_book = (?)";
    public static final String SQL_BOOK_FIND_AND_SORT_BY_TITLE = "SELECT id_book, title, yearPublishing, pages, authors FROM books WHERE title = (?) ORDER BY title";
    public static final String SQL_BOOK_FIND_AND_SORT_BY_YEAR_PUBLISHING = "SELECT id_book, title, yearPublishing, pages, authors FROM books WHERE yearPublishing = (?) ORDER BY yearPublishing";
    public static final String SQL_BOOK_FIND_AND_SORT_BY_AUTHOR = "SELECT id_book, title, yearPublishing, pages, authors FROM books WHERE  authors LIKE (%?%) ORDER BY authors";
    public static final String SQL_BOOK_FIND_AND_SORT_BY_PAGES = "SELECT id_book, title, yearPublishing, pages, authors FROM books WHERE pages = (?) ORDER BY pages";
    public static final String SQL_BOOK_DELETE_BY_ID = "DELETE FROM books WHERE id_book = (?)";
    public static final String SQL_BOOK_DELETE_WITHOUT_ID = "DELETE FROM books WHERE title = (?) AND yearPublishing = (?) AND pages = (?) AND authors = (?)";
    public static final String SQL_BOOK_UPDATE_BY_ID = "UPDATE books SET title = (?) AND yearPublishing = (?) AND pages = (?) AND authors = (?) WHERE id_book = (?)";
    public static final String SQL_BOOK_COUNT_QUANTITY = "SELECT COUNT(DISTINCT id_book) FROM books";

}
