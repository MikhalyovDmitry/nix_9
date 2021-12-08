package ua.com.alevel.view.controller;

import ua.com.alevel.FromFile;
import ua.com.alevel.persistence.entity.Author;
import ua.com.alevel.persistence.entity.Book;
import ua.com.alevel.persistence.entity.BookAuthor;
import ua.com.alevel.service.AuthorService;
import ua.com.alevel.service.BookAuthorService;
import ua.com.alevel.service.BookService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookController {

    private final BookService bookService = new BookService();
    private final AuthorService authorService = new AuthorService();
    private final BookAuthorService bookAuthorService = new BookAuthorService();
    private final AuthorController authorController = new AuthorController();

    public void run() throws InstantiationException, IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
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

    private void crud(String position, BufferedReader reader) throws InstantiationException, IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
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

    private void create(BufferedReader reader) throws NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        System.out.println("Введите название новой книги:");
        String name = notNullInput(reader);
        System.out.println("Введите имя автора, или нескольких, разделенных пробелом:");
        List<Author> authors = inputAuthorList(reader);

        Book book = new Book();
        book.setName(name);
        bookService.create(book);

        for (Author author: authors) {
            BookAuthor relation = new BookAuthor();
            relation.setBookId(book.getId());
            relation.setAuthorId(author.getId());
            bookAuthorService.create(relation);
        }
    }

    private void update(BufferedReader reader) throws InstantiationException, IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        System.out.println("Введите Id книги, которую хотите изменить:");
        Book book = notNullBookById(reader);
        if (book == null) {
            System.out.println("Id не был введен корректно");
            return;
        }

        List<Author> thisBookAuthors = FromFile.findRelation(Author.class, book.getId());
        for (Author author : thisBookAuthors) {
            bookAuthorService.delete(book.getId(), author.getId());
        }

        System.out.println("Введите новое название книги:");
        String name = notNullInput(reader);
        System.out.println("Введите новое имя автора, или нескольких, разделенных пробелом:");
        List<Author> authors = inputAuthorList(reader);

        book.setName(name);
        bookService.update(book);

        for (Author author: authors) {
            BookAuthor relation = new BookAuthor();
            relation.setBookId(book.getId());
            relation.setAuthorId(author.getId());
            bookAuthorService.create(relation);
        }
    }

    private void delete(BufferedReader reader) throws InstantiationException, IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        System.out.println("Введите Id книги, которую хотите удалить:");
        Book book = notNullBookById(reader);
        if (book == null) {
            System.out.println("Id не был введен корректно");
            return;
        }

        List<Author> authorsList = FromFile.findRelation(Author.class, book.getId());
        for (Author author : authorsList) {
            bookAuthorService.delete(book.getId(), author.getId());
        }

        boolean deleteResult = bookService.delete(book.getId());
        if (deleteResult) {
            System.out.println("Книга удалена!");
        }
    }

    private void findById(BufferedReader reader) throws InstantiationException, IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        System.out.println("Введите Id книги, которую хотите найти:");
        Book book = notNullBookById(reader);
        if (book == null) {
            System.out.println("Id не был введен корректно");
        } else {
            bookOutput(book);
            separatingLine();
        }
    }

    private void findAll() throws NoSuchFieldException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        List<Book> books = bookService.findAll();
        if (books != null && books.size() != 0) {
            for (Book book : books) {
                bookOutput(book);
            }
            separatingLine();
        } else {
            System.out.println("Книг не найдено!");
        }
    }

    private void findAllAuthorBooks(BufferedReader reader) throws InstantiationException, IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        System.out.println("Введите id автора:");
        Author author = authorController.notNullAuthorById(reader);

        List<Book> books = authorService.findAllAuthorBooks(author);
        System.out.println("Книги, найденные по указанному автору:");
        if (books != null && books.size() != 0) {
            for (Book book : books) {
                bookOutput(book);
            }
            separatingLine();
        } else {
            System.out.println("Книг не найдено!");
        }
    }

    public void bookOutput(Book book) throws NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<Author> authorsList = FromFile.findRelation(Author.class, book.getId());
        StringBuilder authors = new StringBuilder();
        for (int i = 0; i < authorsList.size(); i++) {
            Author author = authorsList.get(i);
            authors.append(author.getName());
            if (author.isDeleted()) authors.append(" (DELETED)");
            if (i != authorsList.size() - 1) authors.append(", ");
        }
        separatingLine();
        System.out.println("Название       " + book.getName());
        System.out.println("Автор(ы)       " + authors);
        System.out.println("ID Книги       " + book.getId());
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

    public static List<String> inputAuthors (BufferedReader reader) {
        String inputString = null;
        try {
            do {
                inputString = reader.readLine();
                if (inputString.isEmpty())
                    System.out.println("Ошибка! Хотя бы один символ!");
            }
            while (inputString.isEmpty());
        } catch (IOException e) {
            System.out.println("Error: = " + e.getMessage());
        }
        return Arrays.stream(inputString.split(" ")).toList();
    }

    public Book notNullBookById(BufferedReader reader) throws InstantiationException, IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        Book result = null;
        String id;
        try {
            do {
                id = reader.readLine();
                if (id.isEmpty()) {
                    System.out.println("Хотя бы один символ!");
                    continue;
                }
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

    public List<Author> inputAuthorList(BufferedReader reader) throws NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<String> authorNames = inputAuthors(reader);
        List<Author> authors = new ArrayList<>();
        for (String authorName: authorNames) {
            Author author = new Author();
            if (authorService.findByName(authorName) == null) {
                author.setName(authorName);
                author.setDeleted(false);
                authorService.create(author);
            } else {
                author = authorService.findByName(authorName);
            }
            authors.add(author);
        }
        return authors;
    }

    private void separatingLine() {
        System.out.println("──────────────────────────────────────────────");
    }
}
