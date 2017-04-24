package org.perepechayev.scraper.domain;

import org.junit.Test;
import org.junit.Before;

import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 20.04.2017.
 */

public class KeyWordTest {

    private KeyWord keyWord;

    @Before
    public void setUp(){
        keyWord = new KeyWord("Greece");
    }

    @Test
    public void testCreate(){
        assertEquals("Greece", keyWord.getKeyWord());
    }

    @Test
    public void testMatchPattern(){
        assertEquals("\\bGreece\\b", keyWord.getMatchPattern().pattern());
        assertEquals(Pattern.CASE_INSENSITIVE, keyWord.getMatchPattern().flags());
    }
}
