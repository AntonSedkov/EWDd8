package test.epam.bookstore.parser;

import by.epam.bookstore.parser.AuthorParser;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class AuthorParserTest {

    @Test
    public void testParseAuthors() {
        String example = " Stephen King , James Joyce,Neil Gaiman";
        String[] expected = new String[]{"Stephen King", "James Joyce", "Neil Gaiman"};
        String[] actual = AuthorParser.parseAuthors(example);
        assertEquals(actual, expected);
    }

    @Test
    public void testFalseParseAuthors() {
        String example = " Stephen King , James Joyce,Neil Gaiman";
        String[] expected = new String[]{" Stephen King ", " James Joyce", "Neil Gaiman   "};
        String[] actual = AuthorParser.parseAuthors(example);
        assertNotEquals(actual, expected);
    }

}