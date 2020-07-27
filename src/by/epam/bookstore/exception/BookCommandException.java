package by.epam.bookstore.exception;

public class BookCommandException extends Exception {

    public BookCommandException() {
        super();
    }

    public BookCommandException(String message) {
        super(message);
    }

    public BookCommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookCommandException(Throwable cause) {
        super(cause);
    }

}
