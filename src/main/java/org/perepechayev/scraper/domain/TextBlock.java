package org.perepechayev.scraper.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by user on 21.04.2017.
 */
public class TextBlock {

    private String text;

    public TextBlock(String text){
        this.text = text;
    }

    public String getText(){
        return text;
    }

    public List<String> getSentences(){
        String[] sentencesArr = getText().split("\\.");
        for(int i=0; i<sentencesArr.length; i++){
            sentencesArr[i] = sentencesArr[i].trim();
        }
        return Arrays.asList(sentencesArr);
    }

}
