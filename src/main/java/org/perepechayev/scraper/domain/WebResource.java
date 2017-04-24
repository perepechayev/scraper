package org.perepechayev.scraper.domain;

import org.perepechayev.scraper.exception.ScraperException;
import org.perepechayev.scraper.parser.HtmlParser;

import javax.swing.text.html.HTML;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by user on 20.04.2017.
 */
public class WebResource {

    private String url;
    private List<TextBlock> textBlocks = new ArrayList<>();
    private Pattern numCharOccurencesPattern;

    public WebResource(String url){
        this.url = url;
        numCharOccurencesPattern = Pattern.compile("[^\\p{L}1234567890]");
    }

    public String getUrl(){
        return url;
    }

    public void parseResourcePage(String html, HtmlParser parser) throws ScraperException {
        this.textBlocks = parser.parseString(html);
    }

    public List<TextBlock> getTextBlocks(){
        return textBlocks;
    }

    public int getNumCharOccurences(){
        int count = 0;
        Matcher matcher;
        for(TextBlock textBlock: getTextBlocks()){
            matcher = getNumCharOccurencesPattern().matcher(textBlock.getText());
            count += matcher.replaceAll("").length();
        }
        return count;
    }

    public int getNumKeyWordMatches(KeyWord keyWord){
        int count = 0;
        Matcher matcher;
        for(TextBlock textBlock: getTextBlocks()){
            matcher = keyWord.getMatchPattern().matcher(textBlock.getText());
            while(matcher.find()){
                count++;
            }
        }
        return count;
    }

    public List<String> getKeyWordMatchedSentences(KeyWord keyWord){
        List<String> matchedSentences = new ArrayList<>();

        Matcher matcher;
        for(TextBlock textBlock: getTextBlocks()){
            List<String> sentences = textBlock.getSentences();
            for(String sentence: sentences){
                matcher = keyWord.getMatchPattern().matcher(sentence);
                if(matcher.find()){
                    matchedSentences.add(sentence);
                }
            }
        }

        return matchedSentences;
    }

    private Pattern getNumCharOccurencesPattern(){
        return numCharOccurencesPattern;
    }

}
