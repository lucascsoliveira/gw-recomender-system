/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.coutinho.lucas.guesswork.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
public class KNearestNeighborTest {
    
    private static final List<Document> expectedNeighbors;
    private static final List<Document> trainingDocuments;
    private static Document testDocument;
    private static KNearestNeighbor kNearestNeighbor;
    
    static {
        expectedNeighbors = new ArrayList<>();
        trainingDocuments = new ArrayList<>();
    }
    
    @BeforeClass
    public static void setUpClass() {
        //Run before all tests
        //Test Document
        testDocument = new Document("Test_001", "pesquisa resultado resultado descoberta");

        //Training Documents
        trainingDocuments.clear();
        trainingDocuments.add(new Document("Training_001", "pesquisa resultado resultado descoberta", "science"));
        trainingDocuments.add(new Document("Training_002", "bola bola gol gol romario", "sport"));
        trainingDocuments.add(new Document("Training_003", "bola bola bola bola gol gol neymar", "sport"));
        trainingDocuments.add(new Document("Training_004", "pesquisa pesquisa resultado bola", "science"));
        
        kNearestNeighbor = new KNearestNeighbor(trainingDocuments);
    }
    
    @AfterClass
    public static void tearDownClass() {
        //Run after all tests
    }
    
    @Before
    public void setUp() {
        //Run before each test
        expectedNeighbors.clear();
    }
    
    @After
    public void tearDown() {
        //Run after each test
    }

    /**
     * Test of getAllNeighbors method, of class KNearestNeighbor.
     */
    @Test
    public void testGetAllNeighbors() {
        //Add expected neighbors
        expectedNeighbors.add(trainingDocuments.get(0));
        expectedNeighbors.add(trainingDocuments.get(3));
        expectedNeighbors.add(trainingDocuments.get(1));
        expectedNeighbors.add(trainingDocuments.get(2));

        //Get test document
        Iterator<Document> knnIterator = kNearestNeighbor.getAllNeighbors(testDocument);
        Iterator<Document> expectedKNNIterator = expectedNeighbors.iterator();
        
        while (knnIterator.hasNext() && expectedKNNIterator.hasNext()) {
            assertEquals(expectedKNNIterator.next(), knnIterator.next());
        }
        
        assertFalse(knnIterator.hasNext() || expectedKNNIterator.hasNext());
    }

    /**
     * Test of getKNeighbors method, of class KNearestNeighbor where K is less
     * than zero.
     */
    @Test
    public void testGetKNeighbors_K_LessThanZero() {
        Integer K = -10;
        Integer counterK = 0;
        Iterator<Document> knnIterator = kNearestNeighbor.getKNeighbors(testDocument, K);
        
        while (knnIterator.hasNext()) {
            knnIterator.next();
            counterK++;
        }
        
        assertEquals(new Integer(0), counterK);
    }

    /**
     * Test of getKNeighbors method, of class KNearestNeighbor.
     */
    @Test
    public void testGetKNeighbors() {
        //Add expected neighbors
        expectedNeighbors.add(trainingDocuments.get(0));
        expectedNeighbors.add(trainingDocuments.get(3));
        expectedNeighbors.add(trainingDocuments.get(1));
        expectedNeighbors.add(trainingDocuments.get(2));
        
        Integer k = 2;
        Integer counterK = 0;

        //Get test document
        Iterator<Document> knnIterator = kNearestNeighbor.getKNeighbors(testDocument, k);
        Iterator<Document> expectedKNNIterator = expectedNeighbors.iterator();
        
        while (knnIterator.hasNext() && expectedKNNIterator.hasNext()) {
            assertEquals(expectedKNNIterator.next(), knnIterator.next());
            counterK++;
        }
        
        assertEquals(k, counterK);
    }

    /**
     * Test of getKNeighbors method, of class KNearestNeighbor where K is
     * greater than number of documents.
     */
    @Test
    public void testGetKNeighbors_K_GreaterThanNumberOfDocuments() {
        //Add expected neighbors
        expectedNeighbors.add(trainingDocuments.get(0));
        expectedNeighbors.add(trainingDocuments.get(3));
        expectedNeighbors.add(trainingDocuments.get(1));
        expectedNeighbors.add(trainingDocuments.get(2));
        
        Integer k = 10;
        Integer counterK = 0;

        //Get test document
        Iterator<Document> knnIterator = kNearestNeighbor.getKNeighbors(testDocument, k);
        Iterator<Document> expectedKNNIterator = expectedNeighbors.iterator();
        
        while (knnIterator.hasNext() && expectedKNNIterator.hasNext()) {
            assertEquals(expectedKNNIterator.next(), knnIterator.next());
            counterK++;
        }
        
        assertTrue(counterK < k);
    }
}
