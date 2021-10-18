package ua.com.alevel;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class StringsMain {

    public static void main(String[] args) {
        int arguments = 0;
        String line = "";
        String subLine = "";
        int indexIn = 0;
        int indexOut = 0;

        do {
            System.out.println("Введите вариант метода reverse: \n" +
                    "1: reverse(String src)  - обычный реверс строки. \n" +
                    "2: reverse(String src)  - реверс строки по словам. \n" +
                    "3: reverse(String src, String dest)  - реверс по указанной подстроке в строке. \n" +
                    "4: reverse(String src, int firstIndex, int lastIndex) - реверс строки, учитывая\n " +
                    "начальный и конечный индекс строки, реверс которой необходимо осуществить.\n" +
                    "0: Выход из программы");
            arguments = parseIntWithoutExceptions(readLineWithoutExceptions());
            switch (arguments) {
                case 0:
                    System.out.println("Выход из программы.");
                    break;
                case 1:
                    System.out.println("Введите строку String src для реверса:");
                    line = readLineWithoutExceptions();
                    System.out.println(StringReverse.simple(line));
                    break;
                case 2:
                    System.out.println("Введите строку String src для реверса по словам:");
                    line = readLineWithoutExceptions();
                    System.out.println(StringReverse.byWords(line));
                    break;
                case 3:
                    System.out.println("Введите строку String src и подстроку String dest, \n" +
                            "по которой будет произведен реверс:");
                    line = readLineWithoutExceptions();
                    subLine = readLineWithoutExceptions();
                    System.out.println(StringReverse.bySubstring(line, subLine));
                    break;
                case 4:
                    System.out.println("Введите строку String src, начальный и конечный индекс подстроки\n" +
                            "int firstIndex, int lastIndex по которым будет произведен реверс:");
                    line = readLineWithoutExceptions();
                    indexIn = parseIntWithoutExceptions(readLineWithoutExceptions());
                    indexOut = parseIntWithoutExceptions(readLineWithoutExceptions());
                    System.out.println(StringReverse.byIndexes(line, indexIn, indexOut));
                    break;
                default:
                    System.out.println("Некорректно введен вариант исполнения метода. \n" +
                            "Пожалуйста, выберите из списка выше!");
            }
        }
        while (arguments != 0);
    }

    public static int parseIntWithoutExceptions(String number) {
        int defaultValue = 99999;
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
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
