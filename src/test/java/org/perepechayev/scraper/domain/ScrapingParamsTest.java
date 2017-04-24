package org.perepechayev.scraper.domain;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by user on 20.04.2017.
 */

public class ScrapingParamsTest {

    private ScrapingParams params;

    @Before
    public void setUp(){
        params = new ScrapingParams();
    }

    @Test
    public void testWebResources(){
        WebResource wr1 = new WebResource("http://www.cnn.com");
        params.addWebResource(wr1);

        assertEquals(1, params.getWebResources().size());
        assertEquals(wr1, params.getWebResources().get(0));
    }

    @Test
    public void testKeyWords(){
        KeyWord kw = new KeyWord("Greece");
        params.addKeyWord(kw);

        assertEquals(1, params.getKeyWords().size());
        assertEquals(kw, params.getKeyWords().get(0));
    }

    @Test
    public void testVerbosity(){
        assertFalse(params.isVerbose());
        params.setVerbose(true);
        assertTrue(params.isVerbose());
    }

    @Test
    public void testWordsCount(){
        assertFalse(params.isCountWords());
        params.setCountWords(true);
        assertTrue(params.isCountWords());
    }

    @Test
    public void testCountCharacters() {
        assertFalse(params.isCountCharacters());
        params.setCountCharacters(true);
        assertTrue(params.isCountCharacters());
    }

    @Test
    public void testExtractSentences(){
        assertFalse(params.isExtractSentences());
        params.setExtractSentences(true);
        assertTrue(params.isExtractSentences());
    }
}
