package org.perepechayev.scraper.connection;

import org.perepechayev.scraper.domain.WebResource;
import org.perepechayev.scraper.exception.ScraperException;

/**
 * Created by user on 20.04.2017.
 */
public interface HttpConnection {
    public String getPage(WebResource webResource) throws ScraperException;
}
