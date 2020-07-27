package by.epam.bookstore.controller.command.impl;

import by.epam.bookstore.controller.command.Command;
import by.epam.bookstore.exception.BookCommandException;
import by.epam.bookstore.exception.BookServiceException;
import by.epam.bookstore.model.entity.Book;
import by.epam.bookstore.model.service.BookService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddBookCommand implements Command {

    @Override
    public Map<String, List<Book>> execute(Map<String, String> requestParam) throws BookCommandException {
        Map<String, List<Book>> response = new HashMap<>();
        if (requestParam.containsKey(PARAM_NAME_TITLE) && requestParam.containsKey(PARAM_NAME_YEAR_PUBLISHING)
                && requestParam.containsKey(PARAM_NAME_PAGES) && requestParam.containsKey(PARAM_NAME_AUTHORS)) {
            String title = requestParam.get(PARAM_NAME_TITLE);
            String year = requestParam.get(PARAM_NAME_YEAR_PUBLISHING);
            String pages = requestParam.get(PARAM_NAME_PAGES);
            String author = requestParam.get(PARAM_NAME_AUTHORS);
            try {
                if (BookService.getInstance().addBook(title, year, pages, author)) {
                    List<Book> toResponse = BookService.getInstance().getBooksFromBookStore();
                    response.put("bookList", toResponse);
                }
            } catch (BookServiceException e) {
                throw new BookCommandException(e);
            }
        }
        return response;
    }

}