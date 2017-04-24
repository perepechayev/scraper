package org.perepechayev.scraper.domain;

import java.util.regex.Pattern;

/**
 * Created by user on 20.04.2017.
 */

public class KeyWord {

    private String keyWord;
    private Pattern matchPattern;

    public KeyWord(String keyWord){
        this.keyWord = keyWord;
        matchPattern = Pattern.compile("\\b" + this.keyWord + "\\b", Pattern.CASE_INSENSITIVE);
    }

    public String getKeyWord(){
        return keyWord;
    }

    public Pattern getMatchPattern(){
        return matchPattern;
    }
}
