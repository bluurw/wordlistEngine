package wordlistEngine;

import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.ThreadLocalRandom;

public class EngineTwo {

    public static List<String> wordlistPersonalized(int maxSizeString, String keyWords) {
        // Entrada esperada Array[] nome = {};
        // No campo de texto sera recebido uma String, que tambem la sera feito o .split(" ")
        EngineCommons functions = new EngineCommons(); // Chama a EngineCommons

        String[] elements = keyWords.split(" "); // Recebe String e transforma em array
        List<String> combinations = new ArrayList<>(); // Armazena todas as combinações

        //int maxCombinations = (int) Math.pow(words.length, maxSizeString); // Precisa importar lib
        int maxCombinations = functions.exponentiation(elements.length, maxSizeString);

        while (combinations.size() < maxCombinations) {
            StringBuilder stringCombination = new StringBuilder();

            for (int i = 0; i < maxSizeString; i++) {
                int randIntNumber = ThreadLocalRandom.current().nextInt(0, elements.length);
                stringCombination.append(elements[randIntNumber]);
            }

            String combination = stringCombination.toString();

            if (!combinations.contains(combination)) {
                //System.out.println(combination);
                combinations.add(combination);
            }
        }
        return combinations;
    }
}