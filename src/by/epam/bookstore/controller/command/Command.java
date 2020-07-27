package by.epam.bookstore.controller.command;

import by.epam.bookstore.exception.BookCommandException;
import by.epam.bookstore.model.entity.Book;

import java.util.List;
import java.util.Map;

public interface Command {

    String PARAM_NAME_ID = "id";
    String PARAM_NAME_TITLE = "title";
    String PARAM_NAME_YEAR_PUBLISHING = "year";
    String PARAM_NAME_PAGES = "pages";
    String PARAM_NAME_AUTHORS = "authors";
    String PARAM_NAME_AUTHOR = "author";
    String PARAM_NAME_SORT_TYPE = "sort_type";

    Map<String, List<Book>> execute(Map<String, String> requestParam) throws BookCommandException;

}
