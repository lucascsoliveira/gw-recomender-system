/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.coutinho.lucas.guesswork.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lucas
 */
public class DocumentTest {

    private static final String documentName = "TEST-DOCUMENT";
    private static final String documentText = "Lorem ipsum dolor sit amet consectetur adipiscing elit Aliquam tincidunt ipsum tortor non ornare urna ornare vitae Nunc vitae placerat lectus Quisque dignissim nisi purus quis vestibulum nibh molestie quis Fusce a dolor vitae ante sodales porta In porttitor eros et sem vehicula faucibus Nam eros velit ornare sit amet sapien in sagittis pharetra ligula Pellentesque a enim sed eros pellentesque eleifend Vestibulum vehicula fermentum dolor quis fringilla lorem interdum eget Etiam sollicitudin tincidunt libero sed viverra lacus elementum vitae Interdum et malesuada fames ac ante ipsum primis in faucibus Maecenas eu ante at lectus mollis fermentum in eu lectus";

    private static final Document testDocument = new Document(documentName, documentText);
    private static final Map<String, Integer> hits = new HashMap<>();
    private static final Set<String> setOfWords = new TreeSet<>();

    @BeforeClass
    public static void setUpClass() {
        //Run before all tests
        hits.put("a", 2);
        hits.put("ac", 1);
        hits.put("adipiscing", 1);
        hits.put("aliquam", 1);
        hits.put("amet", 2);
        hits.put("ante", 3);
        hits.put("at", 1);
        hits.put("consectetur", 1);
        hits.put("dignissim", 1);
        hits.put("dolor", 3);
        hits.put("eget", 1);
        hits.put("eleifend", 1);
        hits.put("elementum", 1);
        hits.put("elit", 1);
        hits.put("enim", 1);
        hits.put("eros", 3);
        hits.put("et", 2);
        hits.put("etiam", 1);
        hits.put("eu", 2);
        hits.put("fames", 1);
        hits.put("faucibus", 2);
        hits.put("fermentum", 2);
        hits.put("fringilla", 1);
        hits.put("fusce", 1);
        hits.put("in", 4);
        hits.put("interdum", 2);
        hits.put("ipsum", 3);
        hits.put("lacus", 1);
        hits.put("lectus", 3);
        hits.put("libero", 1);
        hits.put("ligula", 1);
        hits.put("lorem", 2);
        hits.put("maecenas", 1);
        hits.put("malesuada", 1);
        hits.put("molestie", 1);
        hits.put("mollis", 1);
        hits.put("nam", 1);
        hits.put("nibh", 1);
        hits.put("nisi", 1);
        hits.put("non", 1);
        hits.put("nunc", 1);
        hits.put("ornare", 3);
        hits.put("pellentesque", 2);
        hits.put("pharetra", 1);
        hits.put("placerat", 1);
        hits.put("porta", 1);
        hits.put("porttitor", 1);
        hits.put("primis", 1);
        hits.put("purus", 1);
        hits.put("quis", 3);
        hits.put("quisque", 1);
        hits.put("sagittis", 1);
        hits.put("sapien", 1);
        hits.put("sed", 2);
        hits.put("sem", 1);
        hits.put("sit", 2);
        hits.put("sodales", 1);
        hits.put("sollicitudin", 1);
        hits.put("tincidunt", 2);
        hits.put("tortor", 1);
        hits.put("urna", 1);
        hits.put("vehicula", 2);
        hits.put("velit", 1);
        hits.put("vestibulum", 2);
        hits.put("vitae", 4);
        hits.put("viverra", 1);

        setOfWords.addAll(hits.keySet());
    }

    @AfterClass
    public static void tearDownClass() {
        //Run after all tests
    }

    @Before
    public void setUp() {
        //Run before each test
    }

    @After
    public void tearDown() {
        //Run after each test
    }

    /**
     * Test of getHits method, of class Document.
     */
    @Test
    public void testGetHits() {
        for (String word : hits.keySet()) {
            assertSame(testDocument.getHits(word), hits.get(word));
        }
    }

    /**
     * Test of getWords method, of class Document.
     */
    @Test
    public void testGetWords() {
        Iterator<String> words = testDocument.getWords();
        Iterator<String> wordsExpected = setOfWords.iterator();

        while (words.hasNext() && wordsExpected.hasNext()) {
            assertEquals(words.next(), wordsExpected.next());
        }

        assertFalse(words.hasNext() || wordsExpected.hasNext());
    }

    @Test
    public void testGetDistanceFromAnEqualDocument() {
        assertEquals(new Integer(0), testDocument.getDistanceFrom(new Document(documentName, documentText)));
    }

    @Test
    public void testGetDistanceFromADifferentDocument() {
        Document document1 = new Document("Document_001", "test test git octo");
        Document document2 = new Document("Document_002", "test human electric");

        assertEquals(new Integer(5), document1.getDistanceFrom(document2));
        assertEquals(new Integer(5), document2.getDistanceFrom(document1));
    }

}
