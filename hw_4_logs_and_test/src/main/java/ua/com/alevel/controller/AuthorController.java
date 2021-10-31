package ua.com.alevel.controller;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.AuthorService;
import ua.com.alevel.service.BookService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// реализовать удаление всех книг автора при удалении автора


public class AuthorController {

    private final AuthorService authorService = new AuthorService();
    private final BookService bookService = new BookService();

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Выберите, что будете делать:");
        String position;
        try {
            runNavigation();
            while ((position = reader.readLine()) != null && !position.equals("0")) {
                crud(position, reader);
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void runNavigation() {
        separatingLine();
        System.out.println("Нажмите 1: Для создания нового автора");
        System.out.println("Нажмите 2: Для изменения существующего автора");
        System.out.println("Нажмите 3: Для удаления автора");
        System.out.println("Нажмите 4: Для поиска автора по Id");
        System.out.println("Нажмите 5: Для вывода списка всех авторов");
        System.out.println("Нажмите 0: Для выхода");
        separatingLine();
    }

    private void crud(String position, BufferedReader reader) {
        switch (position) {
            case "1" -> create(reader);
            case "2" -> update(reader);
            case "3" -> delete(reader);
            case "4" -> findById(reader);
            case "5" -> findAll();
            default -> System.out.println("Ошибка! \nПопробуйте выбрать из указанных вариантов!");
        }
        runNavigation();
    }

    public Author create(BufferedReader reader) {
        Author author = new Author();
        System.out.println("Введите имя нового автора:");
        String name = notNullInput(reader);
        System.out.println("Введите жанр нового автора:");
        String genre = notNullInput(reader);
        author.setName(name);
        author.setGenre(genre);
        authorService.create(author);
        return author;
    }

    private void update(BufferedReader reader) {
        Author author;
        System.out.println("Укажите Id автора, которого хотите изменить:");
        author = notNullAuthorById(reader);
        if (author == null) {
            System.out.println("Id не был введен корректно");
            return;
        }
        System.out.println("Укажите новое имя автора:");
        String name = notNullInput(reader);
        System.out.println("Укажите новый жанр автора:");
        String genre = notNullInput(reader);
        author.setName(name);
        author.setGenre(genre);
        authorService.update(author);
    }

    private void delete(BufferedReader reader) {
        System.out.println("Введите Id автора, которого хотите удалить (Все книги автора будут удалены!):");
        Author author;
        author = notNullAuthorById(reader);
        if (author == null) {
            System.out.println("Id не был введен корректно");
            return;
        }
        Book[] books = authorService.findAllAuthorBooks(author);
        for (Book book: books) {
            bookService.delete(book.getId());
        }
    }

    private void findById(BufferedReader reader) {
        System.out.println("Укажите Id автора, которого хотите найти:");
        Author author;
        author = notNullAuthorById(reader);
        if (author == null) {
            System.out.println("Id не был введен корректно");
            return;
        }
        authorOutput(author);
    }

    private void findAll() {
        Author[] authors = authorService.findAll();
        if (authors != null && authors.length != 0) {
            for (Author author : authors) {
                authorOutput(author);
            }
            separatingLine();
        } else {
            System.out.println("Авторов не найдено!");
        }
    }

    private void authorOutput(Author author) {
        separatingLine();
        System.out.println("Имя        " + author.getName());
        System.out.println("Жанр       " + author.getGenre());
        System.out.println("ID Автора  " + author.getId());
    }

    private void separatingLine() {
        System.out.println("──────────────────────────────────────────────");
    }

    public static String notNullInput(BufferedReader reader) {
        String result = null;
        try {
            do {
                result = reader.readLine();
                if (result.isEmpty())
                    System.out.println("Ошибка! Название должно содержать хотя бы один символ!");
            }
            while (result.isEmpty());
        } catch (IOException e) {
            System.out.println("Error: = " + e.getMessage());
        }
        return result;
    }

    public static Author notNullAuthorById(BufferedReader reader) {
        AuthorService authorService = new AuthorService();
        Author result = null;
        String id;
        try {
            do {
                id = reader.readLine();
                result = authorService.findById(id);
                if (result == null && !id.equals("0"))
                    System.out.println("Ошибка! Введите правильный Id  или 0 для выхода:");
            }
            while (result == null && !id.equals("0"));
        } catch (IOException e) {
            System.out.println("Error: = " + e.getMessage());
        }
        return result;
    }
}
