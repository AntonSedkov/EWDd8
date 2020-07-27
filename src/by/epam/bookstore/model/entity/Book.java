package by.epam.bookstore.model.entity;

import java.util.*;

public class Book extends Entity {

    private String title;
    private int yearPublishing;
    private int pages;
    private Set<String> authors;

    public Book() {
    }

    public Book(int idBook, String title, int yearPublishing, int pages, String... authors) {
        super(idBook);
        this.title = title;
        this.yearPublishing = yearPublishing;
        this.pages = pages;
        this.authors = new TreeSet<>();
        this.authors.addAll(Arrays.asList(authors));
    }

    public Book(String title, int yearPublishing, int pages, String... authors) {
        this.title = title;
        this.yearPublishing = yearPublishing;
        this.pages = pages;
        this.authors = new TreeSet<>();
        this.authors.addAll(Arrays.asList(authors));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<String> getAuthors() {
        return Collections.unmodifiableSet(authors);
    }

    public void setAuthors(String... authors) {
        this.authors = new TreeSet<>();
        this.authors.addAll(Arrays.asList(authors));
    }

    public int getYearPublishing() {
        return yearPublishing;
    }

    public void setYearPublishing(int yearPublishing) {
        this.yearPublishing = yearPublishing;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public boolean equalsBooks(Book book) {
        if (book == null) return false;
        if (yearPublishing != book.getYearPublishing()) return false;
        if (pages != book.getPages()) return false;
        if (title != null ? !title.equals(book.getTitle()) : book.getTitle() != null) return false;
        return authors != null ? authors.equals(book.authors) : book.authors == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        if (getId() != book.getId()) return false;
        return equalsBooks(book);
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (authors != null ? authors.hashCode() : 0);
        result = 31 * result + yearPublishing;
        result = 31 * result + pages;
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Book.class.getSimpleName() + "[", "]")
                .add("idBook=" + getId())
                .add("title='" + title + "'")
                .add("authors=" + authors)
                .add("yearPublishing=" + yearPublishing)
                .add("pages=" + pages)
                .toString();
    }

}
