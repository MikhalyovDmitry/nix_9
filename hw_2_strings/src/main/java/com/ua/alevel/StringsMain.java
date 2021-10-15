package ua.com.alevel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StringsMain {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        System.out.print("Введите аргументы для метода reverse в формате: \n" +
                "reverse(String src)  - обычный реверс строки. \n" +
                "reverse(String src, String dest)  - реверс по указанной подстроке в строке. \n" +
                "reverse(String src, int firstIndex, int lastIndex) - Вы можете указать\n " +
                "начальный и конечный индекс строки, реверс которой необходимо осуществить");
        try {
            line = br.readLine();
        } catch (Exception e) {
            e.getStackTrace();
        }
        System.out.println(reverse(line));
    }

    public static String reverse (String src) {
        String result = "";
        for (int i = src.length() - 1; i >= 0; i--) {
            result += src.charAt(i);
        }
        return result;
    }
    public static String reverse (String src, String dest) {
        int index = src.indexOf(dest);
        String result = src.substring(0, index) + reverse(dest) + src.substring(index + dest.length());
        return result;
    }
}
