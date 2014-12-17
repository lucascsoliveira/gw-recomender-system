/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.coutinho.lucas.guesswork.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author Lucas
 */
public abstract class DocumentClassifier {

    private static final String NO_CATEGORY = "NO_CATEGORY";

    public static void classify(Document document, List<Document> neighborhood) {
        Map<Document, Integer> distances = new HashMap<>();
        Map<String, Double> weightOfCategory;
        String definedCategory = NO_CATEGORY;

        weightOfCategory = calculateWeightOfDocuments(document, neighborhood, distances);

        Double greatestWeight = 0.0;
        for (Entry<String, Double> entry : weightOfCategory.entrySet()) {
            if (entry.getValue() > greatestWeight) {
                greatestWeight = entry.getValue();
                definedCategory = entry.getKey();
            }
        }

        document.setCategory(definedCategory);
    }

    private static void addWeightToCategory(Map<String, Double> pesoDasCategorias, String categoria, Double peso) {
        if (pesoDasCategorias.containsKey(categoria)) {
            Double pesoCategoria = pesoDasCategorias.get(categoria);
            pesoDasCategorias.put(categoria, pesoCategoria + peso);
        } else {
            pesoDasCategorias.put(categoria, peso);
        }
    }

    private static Integer getMaxDistance(Document document, List<Document> neighborhood, Map<Document, Integer> neighborhoodDistances) {
        Integer maxDistance = 0;
        for (Document neighbor : neighborhood) {
            Integer distanceFromNeighbor = document.getDistanceFrom(neighbor);

            neighborhoodDistances.put(neighbor, distanceFromNeighbor);

            if (distanceFromNeighbor > maxDistance) {
                maxDistance = distanceFromNeighbor;
            }
        }

        return maxDistance;
    }

    private static Map<String, Double> calculateWeightOfDocuments(Document document, List<Document> neighborhood, Map<Document, Integer> distances) {
        Integer maxDistance = getMaxDistance(document, neighborhood, distances);
        Map<String, Double> categotyWeights = new HashMap<>();

        for (Document neighbor : neighborhood) {
            String category = neighbor.getCategory();
            Integer distance = document.getDistanceFrom(neighbor);

            Double weight = (maxDistance.doubleValue() / distance.doubleValue());

            addWeightToCategory(categotyWeights, category, weight);
        }

        return categotyWeights;
    }

}
