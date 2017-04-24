package org.perepechayev.scraper.parser;

import org.perepechayev.scraper.domain.TextBlock;
import org.perepechayev.scraper.exception.ScraperException;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 21.04.2017.
 */
public class DefaultHtmlParser implements HtmlParser {

    @Override
    public List<TextBlock> parseString(String htmlString) throws ScraperException{
        Reader reader = new StringReader(htmlString);

        ParserDelegator pd = new ParserDelegator();
        Parser parser = new Parser();
        try {
            pd.parse(reader, parser, true);
        }catch(IOException exception){
            throw new ScraperException(exception);
        }

        return parser.getTextBlockList();
    }

    private class Parser extends HTMLEditorKit.ParserCallback {

        private List<TextBlock> textBlockList = new ArrayList<>();

        @Override
        public void handleText(char[] data, int pos){
            TextBlock text = new TextBlock(new String(data));
            textBlockList.add(text);
        }

        @Override
        public void handleError(String errorMsg, int pos){
        }

        public List<TextBlock> getTextBlockList(){
            return textBlockList;
        }
    }

}
