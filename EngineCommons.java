package wordlistEngine;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EngineCommons {

    public static int exponentiation(int base, int exponent) {
        if (exponent == 0) {
            return 1;
        }
        return base * exponentiation(base, exponent - 1);
    }

    public static int WriterFile(String filePath, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
            writer.newLine();
            return 1;
        } catch (IOException e) {
            return 0;
        }
    }

}