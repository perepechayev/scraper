package org.perepechayev.scraper.startup;

import org.perepechayev.scraper.exception.ScraperException;

import java.util.List;

/**
 * Created by user on 23.04.2017.
 */
public interface UrlFileReader {
    List<String> getUrlList(String fileName) throws ScraperException;
}
