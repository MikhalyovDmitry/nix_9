package ua.com.alevel;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BaseOperationsMain {

    public static void main(String[] args) {
        int taskChoise;
        do {
            taskChoise = 4;
            System.out.println("Выберите, что будем делать:");
            System.out.println("1. Суммирование цифр в строке");
            System.out.println("2. Подсчет букв в строке");
            System.out.println("3. Вычисление времени конца урока");
            System.out.println("0. Выход");
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                taskChoise = Integer.parseInt(reader.readLine());
            } catch (Exception e) {
                e.getStackTrace();
            }

            switch (taskChoise) {
                case 1: {
                    System.out.println("Эта программа подсчитает сумму всех цифр в строке, которую Вы введете.");
                    Scanner in = new Scanner(System.in);
                    System.out.println("Введите строку: ");
                    String str = in.nextLine();
                    System.out.println(sumOfInts(str));
                    break;
                }
                case 2: {
                    System.out.println("Эта программа подсчитает количество всех букв в строке, которую Вы введете.");
                    Scanner in = new Scanner(System.in);
                    System.out.println("Введите строку: ");
                    String str = in.nextLine();
                    sortLetters(str);
                    break;
                }
                case 3: {
                    Scanner in = new Scanner(System.in);
                    System.out.println("Введите номер урока от 1 до 10, и программа подсчитает время конца урока. ");
                    int num = in.nextInt();
                    if (num < 1 || num > 10) {
                        System.out.println("Номер урока введен некорректно! ");
                        break;
                    }
                    lessonEnds(num);
                    break;
                }
                case 0: {
                    System.out.println("Выход из программы... ");
                    break;
                }
                default : {
                    System.out.print("Некорректно, попробуйте еще раз! ");
                }
            }
        } while (taskChoise > 0);
    }



    public static int sumOfInts(String instr) {
        int result = 0;
        if (instr.length() == 0) return 0;
        for (int i = 0; i < instr.length(); i++) {
            if (Character.isDigit(instr.charAt(i))) {
                result += Character.getNumericValue(instr.charAt(i));
            }
        }
        return result;
    }
    public static void sortLetters(String instr) {
        StringBuilder sb = new StringBuilder(instr);
        int i;
        int count;
        while (sb.length() > 0) {
            i = 0;
            count = 0;
            if (Character.isLetter(sb.charAt(0))) {
                System.out.print(sb.charAt(0) + " - ");
                count++;
                for (int j = 1; j < sb.length(); j++) {
                    if (sb.charAt(j) == sb.charAt(i)) {
                        count++;
                        sb.delete(j, j + 1);
                        j--;
                    }
                }
                System.out.println(count);
            }
            sb.delete(i, i + 1);
        }
    }
    public static void lessonEnds(int l) {
        int hours;
        int minutes;
        int time;
        time = 540 + l * 45 + l / 2 * 5 + (l - 1) / 2 * 15;
        hours = time / 60;
        minutes = time % 60;
        System.out.println(hours + " " + minutes);
    }

}
