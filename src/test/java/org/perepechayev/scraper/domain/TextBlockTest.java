package org.perepechayev.scraper.domain;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 21.04.2017.
 */
public class TextBlockTest {

    @Test
    public void testCreate(){
        String text = "Html contents";
        TextBlock textBlock = new TextBlock(text);
        assertEquals(text, textBlock.getText());
    }

    @Test
    public void testGetSentences(){
        String text = "Html contents. Xml contents ";
        TextBlock textBlock = new TextBlock(text);
        List<String> sentences = textBlock.getSentences();
        assertEquals(2, sentences.size());
        assertEquals("Html contents", sentences.get(0));
        assertEquals("Xml contents", sentences.get(1));
    }
}
