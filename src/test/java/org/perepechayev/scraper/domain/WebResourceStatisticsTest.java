package org.perepechayev.scraper.domain;

import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 23.04.2017.
 */
public class WebResourceStatisticsTest {

    private WebResource webResource;
    private WebResourceStatistics wrStatistics;

    @Before
    public void setUp(){
        webResource = new WebResource("http://cnn.com");
        wrStatistics = new WebResourceStatistics(webResource);
    }

    @Test
    public void testCreate(){
        assertEquals(webResource, wrStatistics.getWebResource());
    }

    @Test
    public void testSetWebResourceStatData(){
        int numCharOccurences = 10;

        wrStatistics.setNumCharOccurences(numCharOccurences);
        assertEquals(numCharOccurences, wrStatistics.getNumCharOccurences());
    }

    @Test
    public void testKeyword(){
        KeyWord keyWord = new KeyWord("Greece");
        KeyWordStatistics kwStatistics = new KeyWordStatistics(keyWord);
        wrStatistics.addKeyWordStatistics(kwStatistics);
        assertEquals(kwStatistics, wrStatistics.getKeyWordStatistics().get(0));
    }

    @Test
    public void testTimes(){
        assertEquals(0, wrStatistics.getScrapingTime(), 0);
        assertEquals(0, wrStatistics.getProcessingTime(), 0);

        double scrapingTime = 10.1;
        double processingTime = 11.2;
        wrStatistics.setScrapingTime(scrapingTime);
        wrStatistics.setProcessingTime(processingTime);
        assertEquals(scrapingTime, wrStatistics.getScrapingTime(), 0);
        assertEquals(processingTime, wrStatistics.getProcessingTime(), 0);
    }
}
