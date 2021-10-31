package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class LevelOneTaskThree {

    public static void run() {
        int[][] triangleABC = triangleInput();
        triangleArea(triangleABC);
    }

    public static int[][] triangleInput() {
        int[] pointA, pointB, pointC;
        boolean wasWrong = false;
        do {
            if (wasWrong) {
                System.out.println("Вы ввели одинаковые точки! Введите координаты заново!");
            }
            System.out.println("Введите первую точку A(x, y):");
            pointA = pointInput();
            System.out.println("Вы ввели точку A(" + pointA[0] + ", " + pointA[1] + ")");
            System.out.println("Введите вторую точку B(x, y):");
            pointB = pointInput();
            System.out.println("Вы ввели точку B(" + pointB[0] + ", " + pointB[1] + ")");
            System.out.println("Введите третью точку C(x, y):");
            pointC = pointInput();
            System.out.println("Вы ввели точку C(" + pointC[0] + ", " + pointC[1] + ")");
            wasWrong = true;
        }
        while (Arrays.equals(pointA, pointB) || Arrays.equals(pointB, pointC) || Arrays.equals(pointC, pointA));
        return new int[][]{pointA, pointB, pointC};
    }

    public static int[] pointInput() {
        int defaultWrongValue = Integer.MAX_VALUE;
        int pointX;
        int pointY = defaultWrongValue;
        do {
            System.out.println("Введите х:");
            pointX = parseIntWithoutExceptions(readLineWithoutExceptions());
            if (pointX == defaultWrongValue) {
                System.out.println("Ошибка! Введите целое число, пожалуйста!");
                continue;
            }
            System.out.println("Введите y:");
            pointY = parseIntWithoutExceptions(readLineWithoutExceptions());
            if (pointY == defaultWrongValue) {
                System.out.println("Ошибка! Введите целое число, пожалуйста!");
            }
        }
        while (pointX == defaultWrongValue || pointY == defaultWrongValue);
        return new int[]{pointX, pointY};
    }

    public static double distance(int[] firstPoint, int[] secondPoint) {
        return Math.sqrt
                (Math.pow(firstPoint[0] - secondPoint[0], 2) + Math.pow(firstPoint[1] - secondPoint[1], 2));
    }

    public static void triangleArea(int[][] triangle) {
        double result;
        double a = distance(triangle[0], triangle[1]);
        double b = distance(triangle[1], triangle[2]);
        double c = distance(triangle[2], triangle[0]);
        double p = (a + b + c) / 2;
        result = Math.sqrt(p * (p - a) * (p - b) * (p - c));
        System.out.println("Площадь заданного Вами треугольника ABC с координатами \n" +
                "A(" + triangle[0][0] + ", " + triangle[0][1] + ") " +
                "B(" + triangle[1][0] + ", " + triangle[1][1] + ") " +
                "C(" + triangle[2][0] + ", " + triangle[2][1] + ") " + "равна   " + result);
    }

    public static int parseIntWithoutExceptions(String number) {
        int defaultWrongValue = Integer.MAX_VALUE;
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return defaultWrongValue;
        }
    }

    public static String readLineWithoutExceptions() {
        String line = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }
}
