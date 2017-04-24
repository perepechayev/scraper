package org.perepechayev.scraper.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 23.04.2017.
 */
public class WebResourceStatistics {

    private WebResource webResource;
    private int numCharOccurences;
    private List<String> sentences = new ArrayList<>();
    private List<KeyWordStatistics> keyWordStatistics = new ArrayList<>();
    private double scrapingTime;
    private double processingTime;

    public WebResourceStatistics(WebResource webResource){
        this.webResource = webResource;
    }

    public WebResource getWebResource(){
        return webResource;
    }

    public void setNumCharOccurences(int numCharOccurences){
        this.numCharOccurences = numCharOccurences;
    }

    public int getNumCharOccurences(){
        return numCharOccurences;
    }

    public void addKeyWordStatistics(KeyWordStatistics keyWordStatistics){
        this.keyWordStatistics.add(keyWordStatistics);
    }

    public List<KeyWordStatistics> getKeyWordStatistics(){
        return keyWordStatistics;
    }

    public void setScrapingTime(double scrapingTime){
        this.scrapingTime = scrapingTime;
    }

    public double getScrapingTime(){
        return scrapingTime;
    }

    public void setProcessingTime(double processingTime){
        this.processingTime = processingTime;
    }

    public double getProcessingTime(){
        return processingTime;
    }
}
