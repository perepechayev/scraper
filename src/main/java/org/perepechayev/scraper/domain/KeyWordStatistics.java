package org.perepechayev.scraper.domain;

import java.util.List;

/**
 * Created by user on 23.04.2017.
 */
public class KeyWordStatistics {

    private KeyWord keyWord;
    private int numKeyWordMatches;
    private List<String> matchedSentenes;

    public KeyWordStatistics(KeyWord keyWord){
        this.keyWord = keyWord;
    }

    public KeyWord getKeyWord(){
        return keyWord;
    }

    public void setNumKeyWordMatches(int numKeyWordMatches){
        this.numKeyWordMatches = numKeyWordMatches;
    }

    public int getNumKeyWordMatches(){
        return numKeyWordMatches;
    }

    public void setMatchedSentences(List<String> matchedSentences){
        this.matchedSentenes = matchedSentences;
    }

    public List<String> getMatchedSentences(){
        return matchedSentenes;
    }
}
