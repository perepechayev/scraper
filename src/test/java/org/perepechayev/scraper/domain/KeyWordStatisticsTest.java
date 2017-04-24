package org.perepechayev.scraper.domain;

import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 23.04.2017.
 */
public class KeyWordStatisticsTest {

    private KeyWord keyWord;
    private KeyWordStatistics kwStatistics;

    @Before
    public void setUp(){
        keyWord = new KeyWord("Greece");
        kwStatistics = new KeyWordStatistics(keyWord);
    }

    @Test
    public void testCreate(){
        assertEquals(keyWord, kwStatistics.getKeyWord());
    }

    @Test
    public void testNumKeyWordMatches(){
        int numMatches = 11;
        kwStatistics.setNumKeyWordMatches(numMatches);
        assertEquals(numMatches, kwStatistics.getNumKeyWordMatches());
    }

    @Test
    public void testMatchesSentences(){
        List<String> sentences = new ArrayList<>();
        sentences.add("Euro country");
        kwStatistics.setMatchedSentences(sentences);
        assertEquals(sentences.get(0), kwStatistics.getMatchedSentences().get(0));
    }
}
