package by.epam.bookstore.controller.command.impl;

import by.epam.bookstore.controller.command.Command;
import by.epam.bookstore.exception.BookCommandException;
import by.epam.bookstore.exception.BookServiceException;
import by.epam.bookstore.model.entity.Book;
import by.epam.bookstore.model.service.BookService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeleteCommand implements Command {

    @Override
    public Map<String, List<Book>> execute(Map<String, String> requestParam) throws BookCommandException {
        Map<String, List<Book>> response = new HashMap<>();
        boolean result = false;
        if (requestParam.containsKey(PARAM_NAME_ID)) {
            String id = requestParam.get(PARAM_NAME_ID);
            try {
                result = BookService.getInstance().removeBookById(id);
            } catch (BookServiceException e) {
                throw new BookCommandException(e);
            }
        }
        if (requestParam.containsKey(PARAM_NAME_TITLE) && requestParam.containsKey(PARAM_NAME_YEAR_PUBLISHING)
                && requestParam.containsKey(PARAM_NAME_PAGES) && requestParam.containsKey(PARAM_NAME_AUTHORS)) {
            String title = requestParam.get(PARAM_NAME_TITLE);
            String year = requestParam.get(PARAM_NAME_YEAR_PUBLISHING);
            String pages = requestParam.get(PARAM_NAME_PAGES);
            String author = requestParam.get(PARAM_NAME_AUTHORS);
            try {
                result = BookService.getInstance().removeBookWithoutId(title, year, pages, author);
            } catch (BookServiceException e) {
                throw new BookCommandException(e);
            }
        }
        if (result) {
            try {
                List<Book> toResponse = BookService.getInstance().getBooksFromBookStore();
                response.put("bookList", toResponse);
            } catch (BookServiceException e) {
                throw new BookCommandException(e);
            }
        }
        return response;
    }

}