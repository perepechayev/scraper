package org.perepechayev.scraper.startup;

import org.perepechayev.scraper.exception.ScraperException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 23.04.2017.
 */
public class DefaultUrlFileReader implements UrlFileReader {

    @Override
    public List<String> getUrlList(String fileName) throws ScraperException {

        List<String> urlList = new ArrayList<>();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException exception) {
            throw new ScraperException(exception);
        }

        String line;
        try {
            while ((line = br.readLine()) != null) {
                urlList.add(line.trim());
            }
        } catch (IOException exception) {
            throw new ScraperException(exception);
        } finally {
            try {
                br.close();
            }catch(IOException exception){
                throw new ScraperException(exception);
            }
        }

        return urlList;
    }
}
