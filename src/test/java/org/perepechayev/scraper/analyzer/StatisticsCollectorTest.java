package org.perepechayev.scraper.analyzer;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.mockito.Mockito;
import org.perepechayev.scraper.connection.DefaultHttpConnection;
import org.perepechayev.scraper.connection.HttpConnection;
import org.perepechayev.scraper.domain.*;
import org.perepechayev.scraper.exception.ScraperException;

import java.util.List;

/**
 * Created by user on 22.04.2017.
 */
public class StatisticsCollectorTest {

    private ScrapingParams scrapingParams;
    private StatisticsCollector collector;
    private HttpConnection connection;
    private WebResource webResource;

    @Before
    public void setUp(){
        scrapingParams = new ScrapingParams();

        webResource = new WebResource("http://cnn.com");
        scrapingParams.addWebResource(webResource);
        scrapingParams.addKeyWord(new KeyWord("Greece"));
        scrapingParams.setVerbose(true);
        scrapingParams.setCountWords(true);
        scrapingParams.setCountCharacters(true);
        scrapingParams.setExtractSentences(true);


        collector = new StatisticsCollector(scrapingParams);
    }

    @Test
    public void testCollectStatistics() throws ScraperException {
        String html = "<html><!-- comment --><head>Page Header<title></title></head><body><form method='POST'><h1>Subsctibe to the news from Greece</h1><table><tr><td>email:</td><td><input type='text'/></td></tr></table><input type='submit' value='subscribe'/><a href='http://cnn.com'>Home</a></form></body></html>";
        connection = Mockito.mock(DefaultHttpConnection.class);
        Mockito.when(connection.getPage(webResource)).thenReturn(html);

        List<WebResourceStatistics> wrStatisticsList = collector.collectStatistics(connection);
        assertEquals(1, wrStatisticsList.size());
        WebResourceStatistics wrStatistics = wrStatisticsList.get(0);
        assertEquals(47, wrStatistics.getNumCharOccurences());

        assertEquals(1, wrStatistics.getKeyWordStatistics().size());

        KeyWordStatistics kwStatistics = wrStatistics.getKeyWordStatistics().get(0);
        assertEquals(1, kwStatistics.getNumKeyWordMatches());
        assertEquals(1, kwStatistics.getMatchedSentences().size());
    }

    @Test
    public void testBenchmarking() throws ScraperException {
        String html = "<html><!-- comment --><head>Page Header<title></title></head><body><form method='POST'><h1>Subsctibe to the news from Greece</h1><table><tr><td>email:</td><td><input type='text'/></td></tr></table><input type='submit' value='subscribe'/><a href='http://cnn.com'>Home</a></form></body></html>";
        connection = Mockito.mock(DefaultHttpConnection.class);
        Mockito.when(connection.getPage(webResource)).thenReturn(html);

        List<WebResourceStatistics> wrStatisticsList = collector.collectStatistics(connection);
        assertTrue(wrStatisticsList.get(0).getScrapingTime() > 0);
        assertTrue(wrStatisticsList.get(0).getProcessingTime() > 0);
    }
}
