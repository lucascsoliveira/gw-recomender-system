/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.coutinho.lucas.guesswork.model;

import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Lucas
 */
public class Document {

    private static final String BLANK_SPACE = " ";
    private static final String EMPTY_STRING = "";
    private static final String UNCATEGORIZED = "none";

    private String name;
    private String text;
    private String category;
    private Map<String, Integer> indexOfWords;

    // Document()   <-- Construtor generico
    private Document() {
        indexOfWords = new HashMap<>();
    }

    // Document(String name, String text)   <-- ArquivoTeste
    public Document(String name, String text) {
        this();
        //TERNARY OP(--clausule--) ?[true]:[false]
        this.name = (name != null) ? name : EMPTY_STRING;
        this.text = (text != null) ? text : EMPTY_STRING;
        this.category = UNCATEGORIZED;

        generateIndexOfWords();
    }

    // Document(String name, String text, String category)  <-- ArquivoTreino
    public Document(String name, String text, String category) {
        this(name, text);
        //TERNARY OPERAT(----clausule----) ? [        true        ] : [   false   ]
        this.category = (category != null) ? category.toLowerCase() : UNCATEGORIZED;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getHits(String word) {
        String lowerCaseWord = word.toLowerCase();
        Integer hits = 0;

        if (indexOfWords.containsKey(lowerCaseWord)) {
            hits = indexOfWords.get(lowerCaseWord);
        }

        return hits;
    }

    public Iterator<String> getWords() {
        return getSortedSet(indexOfWords.keySet()).iterator();
    }

    public Integer getDistanceFrom(Document document) {
        Document documentA = this;
        Document documentB = document;
        Integer distance = 0;
        Iterator<String> wordsInDocumentA = documentA.getWords();
        Iterator<String> wordsInDocumentB = documentB.getWords();

        while (wordsInDocumentA.hasNext()) {
            String word = wordsInDocumentA.next();

            Integer frequencyA = documentA.getHits(word);
            Integer frequencyB = documentB.getHits(word);

            distance += (int) Math.pow((frequencyA - frequencyB), 2);
        }

        while (wordsInDocumentB.hasNext()) {
            String word = wordsInDocumentB.next();

            if (documentA.getHits(word) == 0) {
                distance += (int) Math.pow(documentB.getHits(word), 2);
            }
        }

        return distance;
    }

    private void addWordAtIndex(String word) {
        String lowerCaseWord = word.toLowerCase();

        if (indexOfWords.containsKey(lowerCaseWord)) {
            Integer hits = indexOfWords.get(lowerCaseWord);

            indexOfWords.put(lowerCaseWord, hits + 1);
        } else {
            indexOfWords.put(word.toLowerCase(), 1);
        }
    }

    private Set<String> getSortedSet(Set<String> set) {
        return new TreeSet<>(set);
    }

    private void generateIndexOfWords() {
        String[] listOfWords = text.split(BLANK_SPACE);

        for (String word : listOfWords) {
            addWordAtIndex(word);
        }
    }

}
