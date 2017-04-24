package org.perepechayev.scraper.parser;

import org.junit.Test;
import org.perepechayev.scraper.domain.TextBlock;
import org.perepechayev.scraper.exception.ScraperException;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 21.04.2017.
 */
public class DefaultHtmlParserTest {

    private String simpleHtml = "<html><!-- comment --><head>Page Header<title></title></head><body><form method='POST'><h1>Subsctibe to the news</h1><table><tr><td>email:</td><td><input type='text'/></td></tr></table><input type='submit' value='subscribe'/><a href='http://cnn.com'>Home</a></form></body></html>";

    @Test
    public void testSimpleHtml() throws ScraperException {
        HtmlParser parser = new DefaultHtmlParser();
        List<TextBlock> texts = parser.parseString(simpleHtml);

        assertEquals(4, texts.size());
        assertEquals("Page Header", texts.get(0).getText());
        assertEquals("Home", texts.get(3).getText());
    }
}
