package org.perepechayev.scraper.exception;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by user on 20.04.2017.
 */

public class ScraperExceptionTest {

    @Test
    public void testException(){
        String message = "Unexpected error occured";
        ScraperException se = new ScraperException(message);

        assertEquals(message, se.getMessage());
    }

    @Test
    public void rethrow(){
        Exception e = new Exception("Exception occured");
        ScraperException se = new ScraperException(e);

        assertTrue(se.getMessage().contains(e.getMessage()));
    }
}
