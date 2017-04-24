package org.perepechayev.scraper.exception;

/**
 * Created by user on 20.04.2017.
 */
public class ScraperException extends Exception {

    public ScraperException(String message){
        super(message);
    }

    public ScraperException(Throwable cause){
        super(cause);
    }
}
