/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.coutinho.lucas.guesswork.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import so.coutinho.lucas.guesswork.controller.AplDocumentClassification;
import so.coutinho.lucas.guesswork.model.Document;

/**
 *
 * @author Lucas
 */
public class App {

    public static void main(String[] args) {
        File trainingFiles = new File("data\\train-01.txt");
        File testFiles = new File("data\\test-01.txt");
        Integer K = 4;// K := Number of neighbors;
        Integer N = 4;// N := Number of randomDocs (same category);

        AplDocumentClassification aplDocumentClassification;
        Map<Document, String> realCategoryOfDocumentTest = new HashMap<>();

        // ---------------------------------------------------------------------
        // 1.  Leia a lista de documentos de treino do arquivo train-XX.txt
        List<Document> trainingList = readTrainingDocuments(trainingFiles);

        // 2.  Crie o índice global
        aplDocumentClassification = new AplDocumentClassification(trainingList);

        // 3.  Leia a lista de documentos de teste do arquivo test-XX.txt
        List<Document> testList = readTestDocuments(testFiles, realCategoryOfDocumentTest);

        // 4.  Para cada documento de teste:
        for (Document testDocument : testList) {
            System.out.println(testDocument.getName() + "\t"
                    + "("
                    + realCategoryOfDocumentTest.get(testDocument)
                    + ")");
            System.out.println("----------------------------------------");

            //      5.  Crie o índice do documento
            //      6.  Calcule a distância para todos os documentos do índice global e armazene em uma lista/array.
            //      7.  Ordene os documentos pela distância.
            //      8.  Liste os k com menores distâncias.
            Iterator<Document> kNearestDocuments = aplDocumentClassification.getKNearestDocuments(K, testDocument);
            System.out.println("TEXTOS MAIS PARECIDOS");
            System.out.println("----------------------------------------");
            while (kNearestDocuments.hasNext()) {
                Document nextDocument = kNearestDocuments.next();
                System.out.println(nextDocument.getName()
                        + "\t"
                        + nextDocument.getDistanceFrom(testDocument));
            }
            System.out.println("----------------------------------------");

            //      9.  Calcule a classe do documento
            aplDocumentClassification.classify(testDocument);

            //      10. Adicione os documentos da classe em um array
            List<Document> documents = aplDocumentClassification.getDocumentsByCategory(testDocument.getCategory());

            System.out.println("TEXTOS ALEATORIOS DA MESMA CLASSE ("
                    + testDocument.getCategory() + ")");
            System.out.println("----------------------------------------");

            //      11. Para i de 1 a N:
            for (int i = 0; (i < N && (!documents.isEmpty())); i++) {
                //          12. Escolha um nº aleatório entre 0 e size(array) – 1.
                //          13. Se o nº já foi escolhido:
                //              14. Sorteio novamente.
                //          15. Senão:
                //              16. Liste o documento da posição sorteada.
                //              17. Adicione o documento à lista de sorteados.
                int index = new Random().nextInt(documents.size());
                Document randomDocument = documents.remove(index);
                System.out.println(randomDocument.getName());
            }
            System.out.println("----------------------------------------");
            System.out.println("\n\n\n");
        }
    }

    private static List<Document> readTrainingDocuments(File trainingFiles) {
        List<Document> trainingDocuments = new ArrayList<>();

        try {
            Scanner fileReader = new Scanner(trainingFiles);
            Scanner fileContentReader;

            while (fileReader.hasNext()) {
                String[] rawStrings = fileReader.nextLine().split(" ");
                String fileName = rawStrings[0];
                String fileClass = rawStrings[1];
                String fileText;

                fileContentReader = new Scanner(new File(fileName));
                fileText = fileContentReader.nextLine();

                trainingDocuments.add(new Document(fileName, fileText, fileClass));
            }
        } catch (FileNotFoundException ex) {
            System.out.println("[Training] FileNotFound!");
            System.exit(-1);
        }

        return trainingDocuments;
    }

    private static List<Document> readTestDocuments(File testFiles, Map<Document, String> testFilesCategory) {
        List<Document> testDocuments = new ArrayList<>();

        try {
            Scanner fileReader = new Scanner(testFiles);
            Scanner fileContentReader;

            while (fileReader.hasNext()) {
                String[] rawStrings = fileReader.nextLine().split(" ");
                String fileName = rawStrings[0];
                String fileCategory = rawStrings[1];
                String fileText;

                Document document;

                fileContentReader = new Scanner(new File(fileName));
                fileText = fileContentReader.nextLine();

                document = new Document(fileName, fileText);

                testDocuments.add(document);
                testFilesCategory.put(document, fileCategory);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("[Test] FileNotFound!");
            System.exit(-1);
        }

        return testDocuments;
    }

}
