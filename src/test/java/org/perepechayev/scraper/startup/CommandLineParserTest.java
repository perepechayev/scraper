package org.perepechayev.scraper.startup;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.mockito.Mockito;
import org.perepechayev.scraper.exception.ScraperException;
import org.perepechayev.scraper.domain.ScrapingParams;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 20.04.2017.
 */

public class CommandLineParserTest {

    private CommandLineParser cmParser;
    private UrlFileReader fileReader;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp(){
        cmParser = new CommandLineParser();
        fileReader = Mockito.mock(DefaultUrlFileReader.class);
    }

    @Test
    public void parseArgs_notEnoughParams() throws ScraperException{
        thrown.expect(ScraperException.class);
        thrown.expectMessage("Not enough command line parameters. There must be at least two parameters: URL or file name and a list of keywords.");

        String[] args = {"http://www.cnn.com"};
        cmParser.parse(args, fileReader);
    }

    @Test
    public void parseArgs_tooMuchArgs() throws ScraperException{
        thrown.expect(ScraperException.class);
        thrown.expectMessage("Too much command line parameters.");

        String[] args = {"http://www.cnn.com", "Greece,default", "-v", "-w", "-c", "-e", "-z"};
        cmParser.parse(args, fileReader);
    }

    @Test
    public void parseArgs_oneUrl_http_noFlags() throws ScraperException{
        String[] args = {"http://www.cnn.com", "Greece,default"};
        ScrapingParams params = cmParser.parse(args, fileReader);

        assertEquals(1, params.getWebResources().size());
        assertEquals(args[0], params.getWebResources().get(0).getUrl());

        assertEquals(2, params.getKeyWords().size());
        assertEquals("Greece", params.getKeyWords().get(0).getKeyWord());
        assertEquals("default", params.getKeyWords().get(1).getKeyWord());

        assertFalse(params.isVerbose());
        assertFalse(params.isCountWords());
        assertFalse(params.isCountCharacters());
        assertFalse(params.isExtractSentences());
    }

    @Test
    public void parseArgs_oneUrl_www_noFlags() throws ScraperException{
        String[] args = {"www.cnn.com", "Greece,default"};
        ScrapingParams params = cmParser.parse(args, fileReader);

        assertEquals(1, params.getWebResources().size());
        assertEquals(args[0], params.getWebResources().get(0).getUrl());
    }

    @Test
    public void parseArgs_urlsFromFile_noFlags() throws ScraperException{
        String[] args = {"file.txt", "Greece,default"};

        List<String> urlList = new ArrayList<>();
        urlList.add("http://cnn.com");
        urlList.add("http://bbc.com");
        urlList.add("http://abc.com");

        Mockito.when(fileReader.getUrlList("file.txt")).thenReturn(urlList);

        ScrapingParams params = cmParser.parse(args, fileReader);

        assertEquals(3, params.getWebResources().size());
        assertEquals(urlList.get(0), params.getWebResources().get(0).getUrl());
        assertEquals(urlList.get(1), params.getWebResources().get(1).getUrl());
    }

    @Test
    public void parseArgs_oneUrl_allFlags() throws ScraperException{
        String[] args = {"http://www.cnn.com", "Greece,default", "-v", "-w", "-c", "-e"};
        ScrapingParams params = cmParser.parse(args, fileReader);

        assertEquals(1, params.getWebResources().size());
        assertEquals(args[0], params.getWebResources().get(0).getUrl());

        assertEquals(2, params.getKeyWords().size());
        assertEquals("Greece", params.getKeyWords().get(0).getKeyWord());
        assertEquals("default", params.getKeyWords().get(1).getKeyWord());

        assertTrue(params.isVerbose());
        assertTrue(params.isCountWords());
        assertTrue(params.isCountCharacters());
        assertTrue(params.isExtractSentences());
    }
}
