package by.epam.bookstore.model.connection;

public class StatementSql {

    public static final String SQL_FIND_ALL="SELECT id_book, title, yearPublishing, pages, authors FROM books";
    public static final String SQL_FIND_BY_ID="SELECT title, yearPublishing, pages, authors FROM books WHERE id_book = (?)";
    public static final String SQL_FIND_BY_TITLE="SELECT id_book, yearPublishing, pages, authors FROM books WHERE title = (?)";
    public static final String SQL_FIND_ALL_SORT="SELECT id_book, title, yearPublishing, pages, authors FROM books ORDER BY (?)";
    public static final String SQL_4="";
    public static final String SQL_5="";
    public static final String SQL_6="";
    public static final String SQL_7="";
    public static final String SQL_8="";
    public static final String SQL_9="";
    public static final String SQL_10="";
    public static final String SQL_11="";



}
