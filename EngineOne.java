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
        List<String> combinations = wordlistSystem(3, params);
        System.out.println(combinations);
    }
    */
    
    public static List<String> wordlistSystem(int maxSizeString, String[] options) {
        EngineCommons functions = new EngineCommons(); // Chamando EngineCommons
        String stringMerged = stringMerge(options);
        String[] convertStringArray = stringMerged.split(""); // Recebe String e transforma em array
        List<String> combinations = new ArrayList<>(); // Armazena todas as combinações

        //int maxCombinations = (int) Math.pow(words.length, maxSizeString); // Precisa importar lib
        int maxCombinations = functions.exponentiation(convertStringArray.length, maxSizeString);

        while (combinations.size() < maxCombinations) {
            StringBuilder stringCombination = new StringBuilder();

            for (int i = 0; i < maxSizeString; i++) {
                int randIntNumber = ThreadLocalRandom.current().nextInt(0, convertStringArray.length);
                stringCombination.append(convertStringArray[randIntNumber]);
            }

            String combination = stringCombination.toString();

            if (!combinations.contains(combination)) {
                //System.out.println(combination);
                combinations.add(combination);
            }
        }

        return combinations;
    }

    public static String stringMerge(String[] options) {
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
}