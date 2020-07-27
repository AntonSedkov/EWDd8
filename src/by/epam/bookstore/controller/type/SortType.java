package by.epam.bookstore.controller.type;

import by.epam.bookstore.exception.BookServiceException;
import by.epam.bookstore.model.entity.Book;
import by.epam.bookstore.model.service.BookService;
import org.apache.log4j.Logger;

import java.util.List;

public enum SortType {
    ID {
        {
            try {
                sortedList = BookService.getInstance().getBooksFromBookStore();
            } catch (BookServiceException e) {
                Logger logger = Logger.getLogger(SortType.class);
                logger.error(e);
            }
        }
    },
    TITLE {
        {
            try {
                sortedList = BookService.getInstance().sortBooksByTitle();
            } catch (BookServiceException e) {
                Logger logger = Logger.getLogger(SortType.class);
                logger.error(e);
            }
        }
    },
    YEAR {
        {
            try {
                sortedList = BookService.getInstance().sortBooksByYearPublishing();
            } catch (BookServiceException e) {
                Logger logger = Logger.getLogger(SortType.class);
                logger.error(e);
            }
        }
    },
    PAGES {
        {
            try {
                sortedList = BookService.getInstance().sortBooksByPages();
            } catch (BookServiceException e) {
                Logger logger = Logger.getLogger(SortType.class);
                logger.error(e);
            }
        }
    },
    AUTHOR {
        {
            try {
                sortedList = BookService.getInstance().sortBooksByAuthors();
            } catch (BookServiceException e) {
                Logger logger = Logger.getLogger(SortType.class);
                logger.error(e);
            }
        }
    };

    List<Book> sortedList;

    public List<Book> getSortedList() {
        return sortedList;
    }
}
