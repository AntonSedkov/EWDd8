package by.epam.bookstore.validator;

import by.epam.bookstore.model.entity.Book;

import java.time.LocalDate;
import java.util.List;

public class BookValidator {
    private static final String INTEGER_VALUE = "\\d+";

    public static boolean isDuplicateBook(Book book, List<Book> books) {
        boolean isDuplicate = false;
        for (Book bookFromStore : books) {
            if (bookFromStore.equalsBooks(book)) {
                isDuplicate = true;
            }
        }
        return isDuplicate;
    }

    public static boolean isYear(String yearStr) {
        if (yearStr != null && !yearStr.isBlank() && yearStr.trim().matches(INTEGER_VALUE) && Long.parseLong(yearStr) <= Integer.MAX_VALUE) {
            int year = Integer.parseInt(yearStr);
            return year > 0 && year < LocalDate.now().getYear();
        }
        return false;
    }

    public static boolean isPositiveInteger(String pagesStr) {
        if (pagesStr != null && !pagesStr.isBlank() && pagesStr.trim().matches(INTEGER_VALUE) && Long.parseLong(pagesStr) <= Integer.MAX_VALUE) {
            int pages = Integer.parseInt(pagesStr);
            return pages > 0;
        }
        return false;
    }

    public static boolean isGoodString(String string) {
        return string != null && !string.isBlank();
    }

}