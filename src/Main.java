import by.epam.bookstore.exception.DaoException;
import by.epam.bookstore.model.dao.impl.BookDaoImpl;
import by.epam.bookstore.model.entity.Book;

import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        BookDaoImpl dao = BookDaoImpl.getInstance();

     try {
            Book byID = dao.findByID(1).get();
            System.out.println(byID);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        try {
            List<Book> all = dao.findAll();
            System.out.println(all);
        } catch (DaoException e) {
            e.printStackTrace();
        }






    }
}
