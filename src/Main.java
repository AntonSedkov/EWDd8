import by.epam.bookstore.exception.DaoException;
import by.epam.bookstore.model.dao.impl.BookDao;
import by.epam.bookstore.model.entity.BookItem;
import by.epam.bookstore.model.type.DirectionType;
import by.epam.bookstore.model.type.SortType;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        BookDao bookDao = new BookDao();

        try {
            List<BookItem> books = bookDao.findAll();
            System.out.println(books);
        } catch (DaoException e) {
            System.out.println("Bad res");
        }

        try {
            BookItem book = bookDao.findByID(5);
            System.out.println(book);
        } catch (DaoException e) {
            System.out.println("Bad res");
        }

        try {
            BookItem book = bookDao.findByID(1000);
            System.out.println(book);
        } catch (DaoException e) {
            System.out.println("Bad res");
        }

        try {
            List<BookItem> books = bookDao.findAllWithSorting(SortType.YEAR_PUBLISHING);
            System.out.println(books);
        } catch (DaoException e) {
            System.out.println("Bad res");
        }








    }
}
