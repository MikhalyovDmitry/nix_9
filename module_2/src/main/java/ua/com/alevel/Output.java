package ua.com.alevel;

import java.io.FileWriter;
import java.io.IOException;

public class Output {

    public static void run(String output) {
        try {
            FileWriter fileWriter = new FileWriter("output.txt", true);
            fileWriter.write(output);
            fileWriter.write("\n");
            fileWriter.flush();
            System.out.println();
            System.out.println("Result is written to output.txt");
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
