package org.perepechayev.scraper;

import org.perepechayev.scraper.analyzer.StatisticsCollector;
import org.perepechayev.scraper.connection.DefaultHttpConnection;
import org.perepechayev.scraper.domain.ScrapingParams;
import org.perepechayev.scraper.domain.WebResourceStatistics;
import org.perepechayev.scraper.exception.ScraperException;
import org.perepechayev.scraper.startup.CommandLineParser;
import org.perepechayev.scraper.startup.DefaultUrlFileReader;

import java.util.List;

/**
 * Created by user on 20.04.2017.
 */
public class Scraper {

    public static void main(String[] args) throws ScraperException{

        CommandLineParser cmParser = new CommandLineParser();
        ScrapingParams params = cmParser.parse(args, new DefaultUrlFileReader());

        StatisticsCollector collector = new StatisticsCollector(params);
        List<WebResourceStatistics> wrList = collector.collectStatistics(new DefaultHttpConnection());

        collector.printStatistics(wrList);
    }
}
