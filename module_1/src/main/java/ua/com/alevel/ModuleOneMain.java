package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ModuleOneMain {

    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        do {
            System.out.println();
            System.out.println("Выберите уровень:");
            System.out.println("Нажмите 1: Уровень 1 ");
            System.out.println("Нажмите 2: Уровень 2 ");
            System.out.println("Нажмите 3: Уровень 3 ");
            System.out.println("Нажмите 0: Выход");
            String levelChoice = readLineWithoutExceptions();
            switch (levelChoice) {
                case "1" -> levelOneMenu();
                case "2" -> levelTwoMenu();
                case "3" -> LevelThree.run();
                case "0" -> System.exit(0);
                default -> System.out.println("Ошибка! \nПопробуйте выбрать из указанных вариантов!");
            }
        }
        while (true);
    }

    public static void levelOneMenu() {
        do {
            System.out.println("Выберите задачу:");
            System.out.println("Нажмите 1: Задача 1 ( Количество уникальных символов в массиве чисел )");
            System.out.println("Нажмите 2: Задача 2 ( Определение возможности хода конём )");
            System.out.println("Нажмите 3: Задача 3 ( Вычисление площади треугольника )");
            System.out.println("Нажмите 0: Выход");
            String taskChoice = readLineWithoutExceptions();
            switch (taskChoice) {
                case "1" -> LevelOneTaskOne.run();
                case "2" -> LevelOneTaskTwo.run();
                case "3" -> LevelOneTaskThree.run();
                case "0" -> {
                    return;
                }
                default -> System.out.println("Ошибка! \nПопробуйте выбрать из указанных вариантов!");
            }
        }
        while (true);
    }

    public static void levelTwoMenu() {
        do {
            System.out.println("Выберите задачу:");
            System.out.println("Нажмите 1: Задача 1 ( Задача закрытия скобок )");
            System.out.println("Нажмите 2: Задача 2 ( Определение глубины бинарного дерева )");
            System.out.println("Нажмите 0: Выход");
            String levelChoice = readLineWithoutExceptions();
            switch (levelChoice) {
                case "1" -> LevelTwoTaskOne.run();
                case "2" -> LevelTwoTaskTwo.run();
                case "0" -> {
                    return;
                }
                default -> System.out.println("Ошибка! \nПопробуйте выбрать из указанных вариантов!");
            }
        }
        while (true);
    }

    public static String tryAgain() {
        String choice;
        System.out.println("Повторить операцию?");
        System.out.println("1: Да");
        System.out.println("0: Выход");
        do {
            choice = readLineWithoutExceptions();
            if (!choice.equals("0") && !choice.equals("1")) System.out.println("0 или 1, пожалуйста");
        }
        while (!choice.equals("0") && !choice.equals("1"));
        return choice;
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
