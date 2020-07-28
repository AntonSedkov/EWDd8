package by.epam.bookstore.controller;

import by.epam.bookstore.controller.command.Command;
import by.epam.bookstore.exception.BookCommandException;
import by.epam.bookstore.model.entity.Book;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class InvokeController {
    private static Logger logger = Logger.getLogger(InvokeController.class);

    public void processRequest(String request, Map<String, String> responseParam) {
        Command command = ActionProvider.defineCommand(request);
        Map<String, List<Book>> response;
        try {
            response = command.execute(responseParam);
            if (!response.values().contains(new ArrayList<>())) {
                logger.debug("All done: Redirecting to Concrete page");
            } else {
                logger.debug("All done: Redirecting to Without results page");
            }
        } catch (BookCommandException e) {
            logger.debug("BookCommandException: Redirecting to Exception page");
        }
    }

}
