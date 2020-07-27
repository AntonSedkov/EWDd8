package by.epam.bookstore.controller.type;

import by.epam.bookstore.controller.command.Command;
import by.epam.bookstore.controller.command.impl.AddBookCommand;
import by.epam.bookstore.controller.command.impl.DeleteCommand;
import by.epam.bookstore.controller.command.impl.FindCommand;
import by.epam.bookstore.controller.command.impl.SortCommand;

public enum CommandType {

    ADD {
        {
            this.command = new AddBookCommand();
        }
    },
    DELETE {
        {
            this.command = new DeleteCommand();
        }
    },
    FIND {
        {
            this.command = new FindCommand();
        }
    },
    SORT {
        {
            this.command = new SortCommand();
        }
    };

    Command command;

    public Command getCommand() {
        return command;
    }

}
