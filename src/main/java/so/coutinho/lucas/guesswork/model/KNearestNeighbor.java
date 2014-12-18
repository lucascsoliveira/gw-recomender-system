/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.coutinho.lucas.guesswork.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author Lucas
 */
public class KNearestNeighbor {

    private final List<Document> globalIndex;
    private static final Comparator<Entry<Document, Integer>> comparateEntryByDistance;

    static {
        comparateEntryByDistance = new Comparator<Entry<Document, Integer>>() {

            @Override
            public int compare(Entry<Document, Integer> entry1, Entry<Document, Integer> entry2) {
                return entry1.getValue().compareTo(entry2.getValue());
            }
        };
    }

    private KNearestNeighbor() {
        globalIndex = new ArrayList<>();
    }

    public KNearestNeighbor(List<Document> trainingDocuments) {
        this();

        if (trainingDocuments != null) {
            globalIndex.addAll(trainingDocuments);
        }
    }

    private List<Document> getSortedListByDistance(Set<Map.Entry<Document, Integer>> entrySet) {
        List<Document> sortedList = new ArrayList<>();
        List<Map.Entry<Document, Integer>> sortingList = new ArrayList<>(entrySet);

        sortingList.sort(comparateEntryByDistance);

        for (Entry<Document, Integer> entry : sortingList) {
            sortedList.add(entry.getKey());
        }

        return sortedList;
    }

    private List<Document> getListOfAllNeighbors(Document testDocument) {
        Map<Document, Integer> distances = new HashMap<>();

        //1. Leia a lista de documentos de treino do arquivo train-XX.txt
        //2. Crie o índice global
        //3. Leia a lista de documentos de teste do arquivo test-XX.txt
        //4. Para cada documento de teste:
        //5. Crie o índice do documento
        // --- ALL DONE ---
        //6. Calcule a distância para todos os documentos do índice global e armazene em uma lista/array
        for (Document document : globalIndex) {
            distances.put(document, testDocument.getDistanceFrom(document));
        }

        //7. Ordene os documentos pela distância
        //8. Liste os k com menores distância
        return getSortedListByDistance(distances.entrySet());
    }

    public Iterator<Document> getAllNeighbors(Document testDocument) {
        return getListOfAllNeighbors(testDocument).iterator();
    }

    public Iterator<Document> getKNeighbors(Document testDocument, Integer K) {
        List<Document> allNeighbors = getListOfAllNeighbors(testDocument);

        if (K < 0) {
            return allNeighbors.subList(0, 0).iterator();
        }
        if (K > allNeighbors.size()) {
            return allNeighbors.iterator();
        }

        return allNeighbors.subList(0, K).iterator();
    }

    public List<Document> getDocumentsByCategory(String category) {
        List<Document> documentsByCategory = new ArrayList<>();

        for (Document document : globalIndex) {
            if (document.getCategory().equals(category)) {
                documentsByCategory.add(document);
            }
        }

        return documentsByCategory;
    }

}
