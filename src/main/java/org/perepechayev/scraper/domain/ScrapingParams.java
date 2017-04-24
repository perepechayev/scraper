package org.perepechayev.scraper.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by user on 20.04.2017.
 */

public class ScrapingParams {

    private List<WebResource> webResources = new ArrayList<>();
    private List<KeyWord> keyWords = new ArrayList<>();

    private boolean verbose;
    private boolean countWords;
    private boolean countCharacters;
    private boolean extractSentences;

    public void addWebResource(WebResource webResource){
        webResources.add(webResource);
    }

    public List<WebResource> getWebResources(){
        return webResources;
    }

    public void addKeyWord(KeyWord keyWord){
        keyWords.add(keyWord);
    }

    public List<KeyWord> getKeyWords(){
        return keyWords;
    }

    public void setVerbose(boolean verbose){
        this.verbose = verbose;
    }

    public boolean isVerbose(){
        return verbose;
    }

    public void setCountWords(boolean countWords){
        this.countWords = countWords;
    }

    public boolean isCountWords(){
        return countWords;
    }

    public void setCountCharacters(boolean countCharacters){
        this.countCharacters = countCharacters;
    }

    public boolean isCountCharacters(){
        return countCharacters;
    }

    public void setExtractSentences(boolean extractSentences){
        this.extractSentences = extractSentences;
    }

    public boolean isExtractSentences(){
        return extractSentences;
    }
}
