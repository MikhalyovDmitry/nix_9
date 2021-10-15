package com.ua.alevel;


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
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        do {
            System.out.println("Введите вариант метода reverse: \n" +
                    "1: reverse(String src)  - обычный реверс строки. \n" +
                    "2: reverse(String src, String dest)  - реверс по указанной подстроке в строке. \n" +
                    "3: reverse(String src, int firstIndex, int lastIndex) - реверс строки, учитывая\n " +
                    "начальный и конечный индекс строки, реверс которой необходимо осуществить.\n" +
                    "0: Выход из программы");
            try {
                arguments = parseIntWithoutExceptions(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }

            switch (arguments) {
                case 0:
                    System.out.println("Выход из программы.");
                    break;
                case 1:
                    System.out.println("Введите строку String src для реверса");
                    try {
                        line = br.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(reverse(line));
                    break;
                case 2:
                    System.out.println("Введите строку String src и подстроку String dest, через  \n" +
                            "по которой будет произведен реверс");
                    try {
                        line = br.readLine();
                        subLine = br.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(reverse(line, subLine));
                    break;
                case 3:
                    System.out.println("Введите строку String src, начальный и конечный индекс подстроки\n" +
                            "int firstIndex, int lastIndex по которым будет произведен реверс");
                    try {
                        line = br.readLine();
                        indexIn = parseIntWithoutExceptions(br.readLine());
                        indexOut = parseIntWithoutExceptions(br.readLine());
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Некорректно введен индекс");
                    }
                    System.out.println(reverse(line, indexIn, indexOut));
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

    public static String reverse (String src) {
        String result = "";
        for (int i = src.length() - 1; i >= 0; i--) {
            result += src.charAt(i);
        }
        return result;
    }
    public static String reverse (String src, String dest) {
        String result;
        int index = src.indexOf(dest);
        if (index == -1) {
            result = "Введенная подстрока не является частью строки!";
            return result;
        }
        result = src.substring(0, index) + reverse(dest) + src.substring(index + dest.length());
        return result;
    }
    public static String reverse(String src, int firstIndex, int lastIndex) {
        if (firstIndex > src.length() || lastIndex > src.length()) {
            return "Ошибка! Индекс подстроки не должен превышать размера исходной строки!!";
        }
        if (firstIndex == 99999 || lastIndex == 99999) {
            System.out.println("Индекс подстроки введен неверно! Введите число, пожалуйста.");
        }
        if (firstIndex > lastIndex) {
            return "Ошибка! Начальный индекс не должен быть больше конечного!";
        }
        String result = src.substring(0, firstIndex) + reverse(src.substring(firstIndex,lastIndex)) + src.substring(lastIndex);
        return result;
    }
}
