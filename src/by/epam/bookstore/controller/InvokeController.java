package by.epam.bookstore.controller;

import by.epam.bookstore.controller.command.Command;
import by.epam.bookstore.exception.BookCommandException;
import by.epam.bookstore.model.entity.Book;

import java.util.List;
import java.util.Map;

public class InvokeController {

    public void processRequest(String request, Map<String, String> responseParam) {
        Command command = ActionProvider.defineCommand(request);
        Map<String, List<Book>> response;
        try {
            response = command.execute(responseParam);
            System.out.println("Good done: go to Concrete Page");
        } catch (BookCommandException e) {
            System.out.println("BookServiceException: go to Exception page");
        }
    }

}
