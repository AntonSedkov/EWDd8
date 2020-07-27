package by.epam.bookstore.model.service;

import by.epam.bookstore.exception.BookServiceException;
import by.epam.bookstore.exception.DaoException;
import by.epam.bookstore.model.dao.impl.BookDaoImpl;
import by.epam.bookstore.model.entity.Book;
import by.epam.bookstore.parser.AuthorParser;
import by.epam.bookstore.validator.BookValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookService {
    private static BookService instance;

    public static BookService getInstance() {
        if (instance == null) {
            instance = new BookService();
        }
        return instance;
    }

    public boolean addBook(String title, String year, String pages, String author) throws BookServiceException {
        BookDaoImpl dao = BookDaoImpl.getInstance();
        boolean result = false;
        if (BookValidator.isGoodString(title) && BookValidator.isYear(year) & BookValidator.isPositiveInteger(pages) && BookValidator.isGoodString(author)) {
            Book book = new Book(title, Integer.parseInt(year), Integer.parseInt(pages), AuthorParser.parseAuthors(author));
            try {
                List<Book> books = dao.findAll();
                if (!BookValidator.isDuplicateBook(book, books)) {
                    result = dao.create(book);
                }
            } catch (DaoException e) {
                throw new BookServiceException("Unable to add book or it's a duplicated book", e);
            }
        }
        return result;
    }

    public boolean removeBookById(String id) throws BookServiceException {
        BookDaoImpl dao = BookDaoImpl.getInstance();
        boolean result = false;
        if (BookValidator.isPositiveInteger(id)) {
            int idBook = Integer.parseInt(id);
            try {
                int maxId = dao.getMaxIndexInBookstore();
                if (idBook <= maxId) {
                    result = dao.delete(idBook);
                }
            } catch (DaoException e) {
                throw new BookServiceException("Problem of removing book or no such id", e);
            }
        }
        return result;
    }

    public boolean removeBookWithoutId(String title, String year, String pages, String author) throws BookServiceException {
        BookDaoImpl dao = BookDaoImpl.getInstance();
        boolean result = false;
        if (BookValidator.isGoodString(title) && BookValidator.isYear(year) & BookValidator.isPositiveInteger(pages) && BookValidator.isGoodString(author)) {
            Book book = new Book(title, Integer.parseInt(year), Integer.parseInt(pages), AuthorParser.parseAuthors(author));
            try {
                result = dao.delete(book);
            } catch (DaoException e) {
                throw new BookServiceException("Problem of removing book", e);
            }
        }
        return result;
    }

    public Optional<Book> findByID(String id) throws BookServiceException {
        BookDaoImpl dao = BookDaoImpl.getInstance();
        Optional<Book> book = Optional.empty();
        if (BookValidator.isPositiveInteger(id)) {
            int idBook = Integer.parseInt(id);
            try {
                int idMax = dao.getMaxIndexInBookstore();
                if (idBook > idMax) {
                    throw new BookServiceException("Book ID is incorrect");
                }
                book = dao.findByID(idBook);
            } catch (DaoException e) {
                throw new BookServiceException(e);
            }
        }
        return book;
    }

    public List<Book> findByTitle(String title) throws BookServiceException {
        BookDaoImpl dao = BookDaoImpl.getInstance();
        List<Book> result = new ArrayList<>();
        if (BookValidator.isGoodString(title)) {
            try {
                result = dao.findAndSortByTitle(title);
            } catch (DaoException e) {
                throw new BookServiceException(e);
            }
        }
        return result;
    }

    public List<Book> findByYearPublishing(String yearPublishing) throws BookServiceException {
        BookDaoImpl dao = BookDaoImpl.getInstance();
        List<Book> result = new ArrayList<>();
        if (BookValidator.isYear(yearPublishing)) {
            int year = Integer.parseInt(yearPublishing);
            try {
                result = dao.findAndSortByYearPublishing(year);
            } catch (DaoException e) {
                throw new BookServiceException(e);
            }
        }
        return result;
    }

    public List<Book> findByPages(String pages) throws BookServiceException {
        BookDaoImpl dao = BookDaoImpl.getInstance();
        List<Book> result = new ArrayList<>();
        if (BookValidator.isPositiveInteger(pages)) {
            int pagesBook = Integer.parseInt(pages);
            try {
                result = dao.findAndSortByYearPublishing(pagesBook);
            } catch (DaoException e) {
                throw new BookServiceException(e);
            }
        }
        return result;
    }

    public List<Book> findByAuthor(String author) throws BookServiceException {
        BookDaoImpl dao = BookDaoImpl.getInstance();
        List<Book> result = new ArrayList<>();
        if (BookValidator.isGoodString(author)) {
            try {
                result = dao.findAndSortByAuthor(AuthorParser.parseAuthors(author));
            } catch (DaoException e) {
                throw new BookServiceException(e);
            }
        }
        return result;
    }

    public List<Book> sortBooksByTitle() throws BookServiceException {
        BookDaoImpl dao = BookDaoImpl.getInstance();
        try {
            return dao.findAndSortByTitle();
        } catch (DaoException e) {
            throw new BookServiceException(e);
        }
    }

    public List<Book> sortBooksByYearPublishing() throws BookServiceException {
        BookDaoImpl dao = BookDaoImpl.getInstance();
        try {
            return dao.findAndSortByYearPublishing();
        } catch (DaoException e) {
            throw new BookServiceException(e);
        }
    }

    public List<Book> sortBooksByPages() throws BookServiceException {
        BookDaoImpl dao = BookDaoImpl.getInstance();
        try {
            return dao.findAndSortByPages();
        } catch (DaoException e) {
            throw new BookServiceException(e);
        }
    }

    public List<Book> sortBooksByAuthors() throws BookServiceException {
        BookDaoImpl dao = BookDaoImpl.getInstance();
        try {
            return dao.findAndSortByAuthor();
        } catch (DaoException e) {
            throw new BookServiceException(e);
        }
    }

    public List<Book> getBooksFromBookStore() throws BookServiceException {
        BookDaoImpl dao = BookDaoImpl.getInstance();
        try {
            return dao.findAll();
        } catch (DaoException e) {
            throw new BookServiceException(e);
        }
    }

}