package org.perepechayev.scraper.analyzer;

import org.perepechayev.scraper.connection.HttpConnection;
import org.perepechayev.scraper.domain.*;
import org.perepechayev.scraper.exception.ScraperException;
import org.perepechayev.scraper.parser.DefaultHtmlParser;
import org.perepechayev.scraper.parser.HtmlParser;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

/**
 * Created by user on 22.04.2017.
 */
public class StatisticsCollector {

    private ScrapingParams scrapingParams;
    private HtmlParser parser;

    public StatisticsCollector(ScrapingParams scrapingParams){
        this.scrapingParams = scrapingParams;
        parser = new DefaultHtmlParser();
    }

    public List<WebResourceStatistics> collectStatistics(HttpConnection connection) throws ScraperException {

        List<WebResourceStatistics> wrStatisticsList = new ArrayList<>();

        for(WebResource webResource: scrapingParams.getWebResources()){
            WebResourceStatistics wrStatistics = initializeWebResourceStatistics(webResource);

            double scrapingStart = System.nanoTime();
            String html = connection.getPage(webResource);
            double scrapingEnd = System.nanoTime();

            double processingStart = System.nanoTime();
            webResource.parseResourcePage(html, parser);

            if(scrapingParams.isCountCharacters()){
                wrStatistics.setNumCharOccurences(webResource.getNumCharOccurences());
            }

            if(scrapingParams.isCountWords()){
                collectKeyWordsMatches(webResource, wrStatistics);
            }

            if(scrapingParams.isExtractSentences()){
                collectExtractedSentences(webResource, wrStatistics);
            }
            double processingEnd = System.nanoTime();
            wrStatistics.setScrapingTime(scrapingEnd - scrapingStart);
            wrStatistics.setProcessingTime(processingEnd - processingStart);

            wrStatisticsList.add(wrStatistics);
        }

        return wrStatisticsList;
    }

    public void printStatistics(List<WebResourceStatistics> wrStatisticsList){
        for(WebResourceStatistics wrStatistics: wrStatisticsList){
            System.out.println("Statistics for web resource " + wrStatistics.getWebResource().getUrl() + ":");
            if(scrapingParams.isCountCharacters()){
                System.out.println("Number of characters on the page: " + wrStatistics.getNumCharOccurences());
            }
            if(!scrapingParams.isCountWords() && !scrapingParams.isExtractSentences()){
                continue;
            }
            for(KeyWordStatistics kwStatistics: wrStatistics.getKeyWordStatistics()){
                if(scrapingParams.isCountWords()){
                    System.out.println("Number of '" + kwStatistics.getKeyWord().getKeyWord() +
                            "' word occurence on the page: " + kwStatistics.getNumKeyWordMatches());
                }

                if(scrapingParams.isExtractSentences()){
                    System.out.println("List of sentences containing the word '"
                            + kwStatistics.getKeyWord().getKeyWord() + "':");
                    for(String sentence: kwStatistics.getMatchedSentences()){
                        System.out.println("   " + sentence);
                    }
                }
                System.out.println("---------------------------------------------------------------");
            }
            if(scrapingParams.isVerbose()){
                Formatter f = new Formatter();
                System.out.println("Scraping time: "
                        + f.format("%.4f", wrStatistics.getScrapingTime()/1000000000) + "s");
                f = new Formatter();
                System.out.println("Processing time: "
                        + f.format("%.4f", wrStatistics.getProcessingTime()/1000000000) + "s");
            }
            System.out.println("===============================================================");
        }
    }

    private void collectKeyWordsMatches(WebResource webResource, WebResourceStatistics wrStatistics){
        for(KeyWordStatistics kwStatistics: wrStatistics.getKeyWordStatistics()){
            kwStatistics.setNumKeyWordMatches(webResource.getNumKeyWordMatches(kwStatistics.getKeyWord()));
        }
    }

    private void collectExtractedSentences(WebResource webResource, WebResourceStatistics wrStatistics){
        for(KeyWordStatistics kwStatistics: wrStatistics.getKeyWordStatistics()){
            kwStatistics.setMatchedSentences(webResource.getKeyWordMatchedSentences(kwStatistics.getKeyWord()));
        }
    }

    private WebResourceStatistics initializeWebResourceStatistics(WebResource webResource){
        WebResourceStatistics wrStatistics = new WebResourceStatistics(webResource);
        for(KeyWord keyWord: scrapingParams.getKeyWords()){
            KeyWordStatistics kwStatistics = new KeyWordStatistics(keyWord);
            wrStatistics.addKeyWordStatistics(kwStatistics);
        }
        return wrStatistics;
    }

}
