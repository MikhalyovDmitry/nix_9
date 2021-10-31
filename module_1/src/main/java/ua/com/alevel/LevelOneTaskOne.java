package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class LevelOneTaskOne {

    public static void run() {
        String choice;
        do {
            String line;
            System.out.println("Введите массив чисел:");
            line = readLineWithoutExceptions();
            int[] inputArray = stringToArray(line);
            System.out.println(uniqueNumbersCount(inputArray));
            choice = ModuleOneMain.tryAgain();
        }
        while (!choice.equals("0"));
    }

    static int uniqueNumbersCount(int[] inputArray) {
        HashSet<Integer> mySet = new HashSet<>();
        for (int x : inputArray) {
            mySet.add(x);
        }
        return mySet.size();
    }

    public static int[] stringToArray(String inputString) {
        int[] primary = new int[inputString.length()];
        int index = 0;
        int secondaryLength = 0;
        for (int i = 0; i < inputString.length(); i++) {
            if (!Character.isDigit(inputString.charAt(i))) {
                System.out.println(inputString.charAt(i) + " - Введенные данные некорректны! Пожалуйста, вводите числа!");
            }
            if (Character.isDigit(inputString.charAt(i))) {
                primary[index] = inputString.charAt(i);
                index++;
                secondaryLength++;
            }
        }
        int[] result = new int[secondaryLength];
        System.arraycopy(primary, 0, result, 0, secondaryLength);
        return result;
    }

    public static String readLineWithoutExceptions() {
        String line = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            line = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }
}
