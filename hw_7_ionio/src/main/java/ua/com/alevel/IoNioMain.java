package ua.com.alevel;

import ua.com.alevel.controller.AuthorController;
import ua.com.alevel.controller.BookController;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IoNioMain {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        mainMenu();
    }

    public static void mainMenu() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            do {
                separatingLine();
                System.out.println("Выберите, с чем будете работать:");
                System.out.println("Нажмите 1: Авторы");
                System.out.println("Нажмите 2: Книги");
                System.out.println("Нажмите 0: Выход");
                String choice = reader.readLine();
                switch (choice) {
                    case "1" -> new AuthorController().run();
                    case "2" -> new BookController().run();
                    case "0" -> System.exit(0);
                    default -> System.out.println("Ошибка! \nПопробуйте выбрать из указанных вариантов!");
                }
            }
            while (true);
        } catch (IOException | NoSuchFieldException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    public static void separatingLine() {
        System.out.println("──────────────────────────────────────────────");
    }
}