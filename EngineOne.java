package wordlistEngine;

import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.ThreadLocalRandom;

public class EngineOne {
    /*
    Options
    wordsUperCase
    wordsLowerCase
    numbs
    specialCharacters
    public static void main(String[] args) {
        String[] params = {"numbs", "specialCharacters"}; // tipos de caracteres que serao combinados
        String elements = mergeElements(params); // chama a funcao
        System.out.println(elements);
        List<String> combinations = wordlistSystem(3, elements);
        System.out.println(combinations);
    }
    */

    public static List<String> wordlistSystem(int maxSizeString, String[] options) {
        String elementsMerge = mergeElements(options);
        String[] elements = elementsMerge.split(""); // Recebe String e transforma em array
        List<String> combinations = new ArrayList<>(); // Armazena todas as combinações

        //int maxCombinations = (int) Math.pow(words.length, maxSizeString); // Precisa importar lib
        int maxCombinations = exponentiation(elements.length, maxSizeString);

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

    public static String mergeElements(String[] options) {
        String words = "abcdefghijklmnopqrstuvwxyz";
        String numbs = "0123456789";
        String specialCharacters = "@!#$%&*.,><";

        List<String> elements = new ArrayList<>();
        for (int i = 0; i < options.length; i++){
            if (options[i].equals("wordsUperCase")) elements.add(words.toUpperCase());
            if (options[i].equals("wordsLowerCase")) elements.add(words);
            if (options[i].equals("numbs")) elements.add(numbs);
            if (options[i].equals("specialCharacters")) elements.add(specialCharacters);
        }
        return String.join("", elements);
        // e necessario dar .split()
    }

    public static int exponentiation(int base, int exponent) {
        if (exponent == 0) {
            return 1;
        }
        return base * exponentiation(base, exponent - 1);
    }
}