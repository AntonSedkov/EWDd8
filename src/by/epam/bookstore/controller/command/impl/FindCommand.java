package by.epam.bookstore.controller.command.impl;

import by.epam.bookstore.controller.command.Command;
import by.epam.bookstore.exception.BookCommandException;
import by.epam.bookstore.exception.BookServiceException;
import by.epam.bookstore.model.entity.Book;
import by.epam.bookstore.model.service.BookService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindCommand implements Command {

    @Override
    public Map<String, List<Book>> execute(Map<String, String> requestParam) throws BookCommandException {
        Map<String, List<Book>> response = new HashMap<>();
        List<Book> toResponse = new ArrayList<>();
        if (requestParam.containsKey(PARAM_NAME_ID)) {
            String id = requestParam.get(PARAM_NAME_ID);
            try {
                if (BookService.getInstance().findByID(id).isPresent()) {
                    Book findBook = BookService.getInstance().findByID(id).get();
                    toResponse.add(findBook);
                }
            } catch (BookServiceException e) {
                throw new BookCommandException("Finding by ID exception", e);
            }
        }
        if (requestParam.containsKey(PARAM_NAME_TITLE)) {
            String title = requestParam.get(PARAM_NAME_TITLE);
            try {
                toResponse = BookService.getInstance().findByTitle(title);
            } catch (BookServiceException e) {
                throw new BookCommandException("Finding by title exception", e);
            }
        }
        if (requestParam.containsKey(PARAM_NAME_YEAR_PUBLISHING)) {
            String year = requestParam.get(PARAM_NAME_YEAR_PUBLISHING);
            try {
                toResponse = BookService.getInstance().findByYearPublishing(year);
            } catch (BookServiceException e) {
                throw new BookCommandException("Finding by year publishing exception", e);
            }
        }
        if (requestParam.containsKey(PARAM_NAME_PAGES)) {
            String pages = requestParam.get(PARAM_NAME_PAGES);
            try {
                toResponse = BookService.getInstance().findByPages(pages);
            } catch (BookServiceException e) {
                throw new BookCommandException("Finding by pages exception", e);
            }
        }
        if (requestParam.containsKey(PARAM_NAME_AUTHOR)) {
            String author = requestParam.get(PARAM_NAME_AUTHOR);
            try {
                toResponse = BookService.getInstance().findByAuthor(author);
            } catch (BookServiceException e) {
                throw new BookCommandException("Finding by author exception", e);
            }
        }
        response.put("findList", toResponse);
        return response;
    }

}