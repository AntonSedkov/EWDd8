package by.epam.bookstore.controller.command.impl;

import by.epam.bookstore.controller.command.Command;
import by.epam.bookstore.controller.type.SortType;
import by.epam.bookstore.exception.BookCommandException;
import by.epam.bookstore.model.entity.Book;
import by.epam.bookstore.validator.BookValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortCommand implements Command {

    @Override
    public Map<String, List<Book>> execute(Map<String, String> requestParam) throws BookCommandException {
        Map<String, List<Book>> response = new HashMap<>();
        List<Book> toResponse;
        if (requestParam.containsKey(PARAM_NAME_SORT_TYPE) && BookValidator.isGoodString(requestParam.get(PARAM_NAME_SORT_TYPE))) {
            String sortParam = requestParam.get(PARAM_NAME_SORT_TYPE);
            try {
                SortType sortType = SortType.valueOf(sortParam.toUpperCase());
                toResponse = sortType.getSortedList();
                response.put("sortedList", toResponse);
            } catch (IllegalArgumentException exception) {
                throw new BookCommandException("Incorrect sort type", exception);
            }
        }
        return response;
    }

}