package ua.com.alevel.view.controller;

import ua.com.alevel.persistence.entity.Author;
import ua.com.alevel.persistence.entity.Book;
import ua.com.alevel.service.AuthorService;
import ua.com.alevel.service.BookService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// реализовать при создании/изменении книги создание нового автора, или выбор существующего автора по Id
// реализовать поиск всех книг автора

public class BookController {

    public final BookService bookService = new BookService();
    private final AuthorService authorService = new AuthorService();
    private final AuthorController authorController = new AuthorController();

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

    public static Book notNullBookById(BufferedReader reader) {
        BookService bookService = new BookService();
        Book result = null;
        String id;
        try {
            do {
                id = reader.readLine();
                result = bookService.findById(id);
                if (result == null && !id.equals("0"))
                    System.out.println("Ошибка! Введите правильный Id  или 0 для выхода:");
            }
            while (result == null && !id.equals("0"));
        } catch (IOException e) {
            System.out.println("Error: = " + e.getMessage());
        }
        return result;
    }

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
        System.out.println("Нажмите 1: Для создания новой книги");
        System.out.println("Нажмите 2: Для изменения существующей книги");
        System.out.println("Нажмите 3: Для удаления книги");
        System.out.println("Нажмите 4: Для поиска книги по Id");
        System.out.println("Нажмите 5: Для вывода списка всех книг");
        System.out.println("Нажмите 6: Для вывода книг указанного автора");
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
            case "6" -> findAllAuthorBooks(reader);
            default -> System.out.println("Ошибка! \nПопробуйте выбрать из указанных вариантов!");
        }
        runNavigation();
    }

    private void create(BufferedReader reader) {
        System.out.println("Введите название новой книги:");
        String name = notNullInput(reader);
        Author author = selectOrCreateAuthor(reader);
        if (author == null) {
            System.out.println("Не удалось создать книгу, автор не указан!");
            return;
        }
        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        bookService.create(book);
    }

    private void update(BufferedReader reader) {
        System.out.println("Введите Id книги, которую хотите изменить:");
        Book book;
        book = notNullBookById(reader);
        if (book == null) {
            System.out.println("Id не был введен корректно");
            return;
        }
        System.out.println("Введите новое название книги:");
        String name = notNullInput(reader);
        Author author = selectOrCreateAuthor(reader);
        if (author == null) {
            System.out.println("Не удалось создать книгу, автор не указан!");
            return;
        }
        book.setName(name);
        book.setAuthor(author);
        bookService.update(book);
    }

    private void delete(BufferedReader reader) {
        System.out.println("Введите Id книги, которую хотите удалить:");
        Book book;
        book = notNullBookById(reader);
        if (book == null) {
            System.out.println("Id не был введен корректно");
            return;
        }
        boolean deleteResult = bookService.delete(book.getId());
        if (deleteResult) {
            System.out.println("Книга удалена!");
        }
    }

    private void findById(BufferedReader reader) {
        Book book;
        System.out.println("Введите Id книги, которую хотите найти:");
        book = notNullBookById(reader);
        if (book == null) {
            System.out.println("Id не был введен корректно");
        } else {
            bookOutput(book);
            separatingLine();
        }
    }

    private void findAll() {
        Book[] books = bookService.findAll();
        if (books != null && books.length != 0) {
            for (Book book : books) {
                bookOutput(book);
            }
            separatingLine();
        } else {
            System.out.println("Книг не найдено!");
        }
    }

    private void findAllAuthorBooks(BufferedReader reader) {
        Author author;
        try {
            System.out.println("Введите Id автора:");
            do {
                String id = reader.readLine();
                author = authorService.findById(id);
                if (author == null)
                    System.out.println("Ошибка! Укажите правильный Id!");
            }
            while (author == null);

            Book[] books = authorService.findAllAuthorBooks(author);
            System.out.println("Книги, найденные по указанному автору:");
            if (books != null && books.length != 0) {
                for (Book book : books) {
                    bookOutput(book);
                }
                separatingLine();
            } else {
                System.out.println("Книг не найдено!");
            }
        } catch (IOException e) {
            System.out.println("Error: = " + e.getMessage());
        }
    }

    private Author selectOrCreateAuthor(BufferedReader reader) {
        System.out.println("Укажите автора книги:");
        System.out.println("Нажмите 1: Выбор из существующих");
        System.out.println("Нажмите 2: Создание нового автора");
        String choice;
        Author author = new Author();
        String id;
        try {
            do {

                choice = reader.readLine();
                switch (choice) {
                    case "1" -> {
                        System.out.println("Введите Id автора:");
                        do {
                            id = reader.readLine();
                            author = authorService.findById(id);
                            if (author == null && !id.equals("0"))
                                System.out.println("Ошибка! Введите правильный Id, или 0 для выхода:");
                        }
                        while (author == null && !id.equals("0"));
                    }
                    case "2" -> author = authorController.create(reader);
                    default -> System.out.println("Ошибка! Введите 1 или 2!");
                }
            }
            while (!choice.equals("1") && !choice.equals("2"));

        } catch (IOException e) {
            System.out.println("Error: = " + e.getMessage());
        }
        return author;
    }

    public void bookOutput(Book book) {
        separatingLine();
        System.out.println("Название   " + book.getName());
        System.out.println("Жанр       " + book.getAuthor().getGenre());
        System.out.println("ID Книги   " + book.getId());
        System.out.println("Автор      " + book.getAuthor().getName());
        System.out.println("ID Автора  " + book.getAuthor().getId());
    }

    private void separatingLine() {
        System.out.println("──────────────────────────────────────────────");
    }
}
