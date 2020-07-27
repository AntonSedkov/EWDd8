package by.epam.bookstore.controller;

import by.epam.bookstore.controller.command.Command;
import by.epam.bookstore.controller.command.impl.EmptyCommand;
import by.epam.bookstore.controller.type.CommandType;

public class ActionProvider {

    public static Command defineCommand(String request) {
        Command currentCommand = null;
        if (request == null || request.isBlank()) {
            return new EmptyCommand();
        }
        try {
            CommandType currentType = CommandType.valueOf(request.toUpperCase());
            currentCommand = currentType.getCommand();
        } catch (IllegalArgumentException exception) {
            return new EmptyCommand();
        }
        return currentCommand;
    }

}
