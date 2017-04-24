package org.perepechayev.scraper.parser;

import org.perepechayev.scraper.domain.TextBlock;
import org.perepechayev.scraper.exception.ScraperException;

import java.util.List;

/**
 * Created by user on 21.04.2017.
 */
public interface HtmlParser {
    public List<TextBlock> parseString(String htmlString) throws ScraperException;
}
