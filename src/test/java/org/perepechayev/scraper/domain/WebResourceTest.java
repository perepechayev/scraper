package org.perepechayev.scraper.domain;

import org.junit.Test;
import org.junit.Before;
import org.mockito.Mockito;
import org.perepechayev.scraper.exception.ScraperException;
import org.perepechayev.scraper.parser.DefaultHtmlParser;
import org.perepechayev.scraper.parser.HtmlParser;

import javax.xml.soap.Text;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by user on 20.04.2017.
 */

public class WebResourceTest {

    private WebResource webResource;

    @Before
    public void setUp(){
        webResource = new WebResource("http://www.cnn.com");
    }

    @Test
    public void testCreate(){
        assertEquals("http://www.cnn.com", webResource.getUrl());
    }

    @Test
    public void testParseResourcePage() throws ScraperException {
        String html = "<html><!-- comment --><head>Page Header<title></title></head><body><form method='POST'><h1>Subsctibe to the news</h1><table><tr><td>email:</td><td><input type='text'/></td></tr></table><input type='submit' value='subscribe'/><a href='http://cnn.com'>Home</a></form></body></html>";

        webResource.parseResourcePage(html, new DefaultHtmlParser());
        List<TextBlock> textBlocks = webResource.getTextBlocks();
        assertEquals(4, textBlocks.size());
        assertEquals("Page Header", textBlocks.get(0).getText());
        assertEquals("Home", textBlocks.get(3).getText());
    }

    @Test
    public void testNumCharOccurences() throws ScraperException{
        TextBlock tb1 = new TextBlock("Text with,123;.!~= characters/[]\n");
        TextBlock tb2 = new TextBlock("Greece is a European country. EOF.");
        List<TextBlock> textBlocks = new ArrayList<>();
        textBlocks.add(tb1);
        textBlocks.add(tb2);

        HtmlParser parser = Mockito.mock(DefaultHtmlParser.class);
        Mockito.when(parser.parseString("<>")).thenReturn(textBlocks);
        webResource.parseResourcePage("<>", parser);
        assertEquals(48, webResource.getNumCharOccurences());
    }

    @Test
    public void testNumKeyWordMatches() throws ScraperException{
        TextBlock tb1 = new TextBlock("igreeces is not country. The country is Greece.");
        TextBlock tb2 = new TextBlock("Greece is a European country; \nGREECE in upper case.");
        List<TextBlock> textBlocks = new ArrayList<>();
        textBlocks.add(tb1);
        textBlocks.add(tb2);

        KeyWord keyWord = new KeyWord("Greece");
        HtmlParser parser = Mockito.mock(DefaultHtmlParser.class);
        Mockito.when(parser.parseString("<>")).thenReturn(textBlocks);
        webResource.parseResourcePage("<>", parser);

        assertEquals(3, webResource.getNumKeyWordMatches(keyWord));
    }

    @Test
    public void testGetKeyWordMatchedSentences() throws ScraperException{
        TextBlock tb1 = new TextBlock("igreeces is not country. The country is Greece.");
        TextBlock tb2 = new TextBlock("Greece is a European country; \nGREECE in upper case.");
        List<TextBlock> textBlocks = new ArrayList<>();
        textBlocks.add(tb1);
        textBlocks.add(tb2);

        KeyWord keyWord = new KeyWord("Greece");
        HtmlParser parser = Mockito.mock(DefaultHtmlParser.class);
        Mockito.when(parser.parseString("<>")).thenReturn(textBlocks);
        webResource.parseResourcePage("<>", parser);

        List<String> matchedSentences = webResource.getKeyWordMatchedSentences(keyWord);
        assertEquals(2, matchedSentences.size());
        assertEquals("The country is Greece", matchedSentences.get(0));
        assertEquals("Greece is a European country; \nGREECE in upper case", matchedSentences.get(1));
    }
}
