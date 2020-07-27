package by.epam.bookstore.controller.type;

import by.epam.bookstore.exception.BookServiceException;
import by.epam.bookstore.model.entity.Book;
import by.epam.bookstore.model.service.BookService;

import java.util.List;

public enum SortType {
    ID {
        {
            try {
                sortedList = BookService.getInstance().getBooksFromBookStore();
            } catch (BookServiceException e) {
                // TODO: 27.07.2020 log.error(e);
            }
        }
    },
    TITLE {
        {
            try {
                sortedList = BookService.getInstance().sortBooksByTitle();
            } catch (BookServiceException e) {
                // TODO: 27.07.2020 log.error(e);
            }
        }
    },
    YEAR {
        {
            try {
                sortedList = BookService.getInstance().sortBooksByYearPublishing();
            } catch (BookServiceException e) {
                // TODO: 27.07.2020 log.error(e);
            }
        }
    },
    PAGES {
        {
            try {
                sortedList = BookService.getInstance().sortBooksByPages();
            } catch (BookServiceException e) {
                // TODO: 27.07.2020 log.error(e);
            }
        }
    },
    AUTHOR {
        {
            try {
                sortedList = BookService.getInstance().sortBooksByAuthors();
            } catch (BookServiceException e) {
                // TODO: 27.07.2020 log.error(e);
            }
        }
    };

    List<Book> sortedList;

    public List<Book> getSortedList() {
        return sortedList;
    }
}
