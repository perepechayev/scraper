package org.perepechayev.scraper.connection;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.perepechayev.scraper.domain.WebResource;
import org.perepechayev.scraper.exception.ScraperException;


/**
 * Created by user on 20.04.2017.
 */
public class DefaultHttpConnection implements HttpConnection {

    @Override
    public String getPage(WebResource webResource) throws ScraperException {

        InputStream is = null;
        String page;
        try {
            URL url = new URL(webResource.getUrl());
            is = url.openStream();

            page = readFromStream(is);
        }catch(MalformedURLException exception){
            throw new ScraperException(exception);
        }catch(IOException exception){
            throw new ScraperException(exception);
        }finally{
            if(is != null){
                try {
                    is.close();
                }catch(IOException exception){
                    throw new ScraperException(exception);
                }
            }
        }

        return page;
    }

    private String readFromStream(InputStream is) throws ScraperException{
        String line;
        StringBuilder builder = new StringBuilder();
        BufferedReader br;
        try {
            String charset = detectCharset(is);
            br = new BufferedReader(new InputStreamReader(is, charset));
        }catch(UnsupportedEncodingException exception){
            throw new ScraperException(exception);
        }catch(IOException exception){
            throw new ScraperException(exception);
        }

        try {
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }
        }catch(IOException exception){
            throw new ScraperException(exception);
        }finally {
            try {
                br.close();
            }catch(IOException exception){
                throw new ScraperException(exception);
            }
        }

        return builder.toString();
    }

    private String getCharsetFromHtmlString(String htmlString){
        Pattern pattern = Pattern.compile("<meta\\s+charset=[\"']([^\"']+)[\"']", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(htmlString);
        if(matcher.find()){
            return matcher.group(1);
        }else{
            return null;
        }
    }

    private String detectCharset(InputStream is) throws UnsupportedEncodingException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String line;
        while ((line = br.readLine()) != null) {
            String charset = getCharsetFromHtmlString(line);
            if (charset != null) {
                return charset;
            }
        }
        return "UTF-8";
    }

}
