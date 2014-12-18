/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.coutinho.lucas.guesswork.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import so.coutinho.lucas.guesswork.model.Document;
import so.coutinho.lucas.guesswork.model.DocumentClassifier;
import so.coutinho.lucas.guesswork.model.KNearestNeighbor;

/**
 *
 * @author Lucas
 */
public class AplDocumentClassification {

    private final KNearestNeighbor kNearestNeighbor;

    public AplDocumentClassification(List<Document> list) {
        kNearestNeighbor = new KNearestNeighbor(list);
    }

    public Iterator<Document> getKNearestDocuments(Integer K, Document document) {
        return kNearestNeighbor.getKNeighbors(document, K);
    }

    public void classify(Document document) {
        Iterator<Document> iteratorNeighbors = kNearestNeighbor.getAllNeighbors(document);
        List<Document> listNeighbors = new ArrayList<>();

        while (iteratorNeighbors.hasNext()) {
            listNeighbors.add(iteratorNeighbors.next());
        }

        DocumentClassifier.classify(document, listNeighbors);
    }

    public List<Document> getDocumentsByCategory(String category) {
        return kNearestNeighbor.getDocumentsByCategory(category);
    }

}
