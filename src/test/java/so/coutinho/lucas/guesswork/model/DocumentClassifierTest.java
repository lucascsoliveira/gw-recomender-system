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
public class DocumentClassifierTest {

    public static Document testDocument;
    public static String testDocumentCategory;
    public static final List<Document> trainingDocuments = new ArrayList<>();

    @BeforeClass
    public static void setUpClass() {
        //Run before all tests
        //Add training documents
        trainingDocuments.add(new Document("Training_001", "pesquisa resultado resultado descoberta", "science"));
        trainingDocuments.add(new Document("Training_002", "bola bola gol gol romario", "sport"));
        trainingDocuments.add(new Document("Training_003", "bola bola bola bola gol gol neymar", "sport"));
        trainingDocuments.add(new Document("Training_004", "pesquisa pesquisa resultado bola", "science"));

        //Add testDocument
        testDocument = new Document("Test_001", "resultado gol descoberta pesquisa pesquisa");
        testDocumentCategory = "science";
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
     * Test of classify method, of class DocumentClassifier.
     */
    @Test
    public void testClassify() {
        KNearestNeighbor kNearestNeighbor = new KNearestNeighbor(trainingDocuments);
        Iterator<Document> iterator = kNearestNeighbor.getAllNeighbors(testDocument);
        List<Document> listOfNeighbors = new ArrayList<>();

        while (iterator.hasNext()) {
            listOfNeighbors.add(iterator.next());
        }

        DocumentClassifier.classify(testDocument, listOfNeighbors);

        assertEquals(testDocumentCategory, testDocument.getCategory());
    }

}
