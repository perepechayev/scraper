package org.perepechayev.scraper.startup;

import java.util.Arrays;
import java.util.List;

import org.perepechayev.scraper.exception.ScraperException;
import org.perepechayev.scraper.domain.KeyWord;
import org.perepechayev.scraper.domain.ScrapingParams;
import org.perepechayev.scraper.domain.WebResource;

/**
 * Created by user on 20.04.2017.
 */
public class CommandLineParser {

    public ScrapingParams parse(String[] args, UrlFileReader fileReader) throws ScraperException{
        validate(args);

        ScrapingParams params = new ScrapingParams();
        setWebResources(args[0], params, fileReader);
        setKeyWords(args[1], params);
        setFlags(Arrays.copyOfRange(args, 2, args.length), params);

        return params;
    }

    private void setWebResources(String webResourceString, ScrapingParams params, UrlFileReader fileReader) throws ScraperException{
        if(webResourceString.startsWith("http://") || webResourceString.startsWith("www.")){
            params.addWebResource(new WebResource(webResourceString));
        }else{
            List<String> urlList = fileReader.getUrlList(webResourceString);
            for(String url: urlList){
                params.addWebResource(new WebResource(url));
            }
        }
    }

    private void setKeyWords(String keyWordsString, ScrapingParams params){
        String[] keyWords = keyWordsString.split(",");

        for(String keyWord: keyWords){
            params.addKeyWord(new KeyWord(keyWord));
        }
    }

    private void setFlags(String[] args, ScrapingParams params){
        List<String> argsList = Arrays.asList(args);
        if(argsList.contains("-v")){
            params.setVerbose(true);
        }
        if(argsList.contains("-w")){
            params.setCountWords(true);
        }
        if(argsList.contains("-c")){
            params.setCountCharacters(true);
        }
        if(argsList.contains("-e")){
            params.setExtractSentences(true);
        }
    }

    private void validate(String[] args) throws ScraperException{
        if(args.length < 2){
            throw new ScraperException("Not enough command line parameters. There must be at least two parameters: URL or file name and a list of keywords.");
        }
        if(args.length > 6){
            throw new ScraperException("Too much command line parameters.");
        }
    }

}
