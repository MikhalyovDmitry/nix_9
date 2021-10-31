package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LevelOneTaskTwo {

    public static void run() {
        int[] start;
        int[] finish;
        String choice;
        do {
            do {
                System.out.println("Введите начальную позицию коня в формате a1:");
                start = inputPosition();
            }
            while (isWrong(start));

            do {
                System.out.println("Введите конечную позицию коня в формате a1:");
                finish = inputPosition();
            }
            while (isWrong(finish));

            draw(start, finish);

            isAvailable(start, finish);
            choice = ModuleOneMain.tryAgain();
        }
        while (!choice.equals("0"));
    }

    public static int[] inputPosition() {
        String inputString;
        inputString = readLineWithoutExceptions();
        int[] result = new int[3];

        if (inputString.length() < 2) {
            inputString += "0";
            System.out.println("Ошибка! Позиция фигуры должна содержать хотя бы два символа!");
            result[2] = 1;
        }
        String abcdefgh = "abcdefgh";
        if (abcdefgh.indexOf(inputString.charAt(0)) == -1) {
            System.out.println("Ошибка! Первый индекс позиции фигуры должен быть в диапазоне a - h ");
            result[2] = 1;
        } else {
            result[0] = letterToIndex(inputString.charAt(0));
        }
        String numbers = "12345678";
        if (numbers.indexOf(inputString.charAt(1)) == -1) {
            System.out.println("Ошибка! Второй индекс позиции фигуры должен быть в диапазоне 1 - 8 ");
            result[2] = 1;
        } else {
            result[1] = Character.getNumericValue(inputString.charAt(1));
        }
        return result;
    }

    public static boolean isWrong(int[] inputArray) {
        return inputArray[2] == 1;
    }

    public static void isAvailable(int[] start, int[] finish) {
        int dx = Math.abs(start[0] - finish[0]);
        int dy = Math.abs(start[1] - finish[1]);
        if (dx == 1 && dy == 2 || dx == 2 && dy == 1) {
            System.out.println("ХОД ВОЗМОЖЕН!");
        } else {
            System.out.println("ХОД НЕВОЗМОЖЕН!");
        }
    }

    public static int letterToIndex(char inputChar) {
        int result = 0;
        if (inputChar == 'a') result = 1;
        if (inputChar == 'b') result = 2;
        if (inputChar == 'c') result = 3;
        if (inputChar == 'd') result = 4;
        if (inputChar == 'e') result = 5;
        if (inputChar == 'f') result = 6;
        if (inputChar == 'g') result = 7;
        if (inputChar == 'h') result = 8;
        return result;
    }

    public static void draw(int[] start, int[] finish) {
        System.out.println("st - стартовая позиция");
        System.out.println("fn - конечная позиция");
        int startPosition = 8 * (8 - start[1]) + start[0] - 1;
        int finishPosition = 8 * (8 - finish[1]) + finish[0] - 1;
        int position = 0;
        int numbers = 8;
        do {
            if (position == 0)
                System.out.print(numbers);
            if (position == startPosition) {
                if (start[0] == 1) {
                    System.out.println();
                    numbers--;
                    System.out.print(numbers);
                }
                System.out.print("st");
                position++;
            }
            if (position == finishPosition) {
                if (finish[0] == 1) {
                    System.out.println();
                    numbers--;
                    System.out.print(numbers);
                }
                System.out.print("fn");
                position++;
            }
            if (position % 8 == 0 && position != 0) {
                System.out.println();
                numbers--;
                System.out.print(numbers);
            }
            if (position % 2 == 0 && (position / 8) % 2 == 0 || position % 2 == 1 && (position / 8) % 2 != 0) {
                System.out.print("▓▓");
            } else {
                System.out.print("░░");
            }
            position++;
        }
        while (position < 64);
        System.out.println();
        System.out.println(" a b c d e f g h");
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
