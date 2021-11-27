package ua.com.alevel;

import java.io.*;
import java.util.*;

public class ModuleTwoMain {

    public static void main(String[] args) {
        menu(readInputTxt());
    }

    public static void menu(List<String> input) {
        if (input == null) {
            System.out.println("file input.txt must contain a data for work!");
            System.exit(0);
        }
        do {
            System.out.println("  What to do with input.txt?");
            System.out.println("1 Date formatting");
            System.out.println("2 Unique name search");
            System.out.println("3 Shortest ways search");
            System.out.println("0 Exit");

            switch (readLineWithoutExceptions()) {
                case "1" -> Date.run(input);
                case "2" -> Name.getFirstUnique(input);
                case "3" -> City.run(input);
                case "0" -> System.exit(0);
                default -> System.out.println("Choose something from the list");
            }
        }
        while (true);
    }

    public static List<String> readInputTxt() {
        List<String> input = new ArrayList<>();
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader("input.txt"));
        } catch (FileNotFoundException e) {
            if (e != null) System.out.println("File input.txt not found");
            return null;
        }
        try {
            while (bufferedReader.ready()) {
                input.add(bufferedReader.readLine());
            }
        } catch (IOException e) {
            if (e != null) System.out.println(e);
        }
        if (input.isEmpty()) {
            System.out.println("File is empty!");
            return null;
        }
        //System.out.println(input);
        return input;
    }

    public static String readLineWithoutExceptions() {
        String line = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do {
            try {
                line = br.readLine();
                if (line.isEmpty()) {
                    System.out.println("Хотя бы один символ, пожалуйста!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        while (line.isEmpty());
        return line;
    }
}

