package by.epam.bookstore.parser;

public class AuthorParser {

    private static final String AUTHOR_SPLITTER = ",";

    public static String[] parseAuthors(String authors) {
        String[] arr = authors.split(AUTHOR_SPLITTER);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i].trim();
        }
        return arr;
    }

}
