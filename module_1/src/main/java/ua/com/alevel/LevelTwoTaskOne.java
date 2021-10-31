package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Строка состоит только из скобок, или другие символы допустимы?
// "Обратите внимание, что пустая строка также считается допустимой" В этом случае false?

public class LevelTwoTaskOne {

    public static void run() {
        System.out.println(matchingBrackets(inputLine()));
    }

    public static boolean matchingBrackets(String input) {
        StringBuilder buffer;
        buffer = new StringBuilder();
        char[] c = input.toCharArray();
        for (char value : c) {
            buffer.append(value);
            if (buffer.length() > 1 &&
                    isPair(buffer.charAt(buffer.length() - 2), buffer.charAt(buffer.length() - 1))) {
                buffer.delete(buffer.length() - 2, buffer.length());
            }
        }
        return buffer.isEmpty();
    }

    public static boolean isPair(char a, char b) {
        return a == '{' && b == '}'
                || a == '[' && b == ']'
                || a == '(' && b == ')';
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

    public static String inputLine() {
        System.out.println("Пожалуйста, введите строку, содержащую только скобки { } [ ] ( )");
        return readLineWithoutExceptions();
    }
}

