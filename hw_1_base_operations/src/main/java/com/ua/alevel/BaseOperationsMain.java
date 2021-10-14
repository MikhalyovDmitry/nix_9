package ua.com.alevel;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BaseOperationsMain {

    public static void main(String[] args) {
        int taskChoise = 0;
        do {
            System.out.println("1. Суммирование цифр в строке");
            System.out.println("2. Подсчет букв в строке");
            System.out.println("3. Вычисление времени конца урока");
            System.out.println("4. Выход");
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                taskChoise = Integer.parseInt(reader.readLine());
            } catch (Exception e) {
                e.getStackTrace();
            }

            switch (taskChoise) {
                case 1: {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Введите строку: ");
                    String str = in.nextLine();
                    System.out.println(sumOfInts(str));
                    break;
                }
                case 2: {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Ведите строку: ");
                    String str = in.nextLine();
                    sortLetters(str);
                    break;
                }
                case 3: {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Введите номер урока от 1 до 10: ");
                    int num = in.nextInt();
                    lessonEnds(num);
                    break;
                }
            }
        } while (taskChoise < 4);
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
