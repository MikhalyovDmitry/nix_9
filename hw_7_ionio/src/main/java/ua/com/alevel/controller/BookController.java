package ua.com.alevel.controller;

import ua.com.alevel.FromFile;
import ua.com.alevel.ToFile;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.entity.BookAuthor;
import ua.com.alevel.service.AuthorService;
import ua.com.alevel.service.BookAuthorService;
import ua.com.alevel.service.BookService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

// реализовать при создании/изменении книги создание нового автора, или выбор существующего автора по Id
// реализовать поиск всех книг автора

public class BookController {

    public final BookService bookService = new BookService();
    private final AuthorService authorService = new AuthorService();
    private final BookAuthorService bookAuthorService = new BookAuthorService();

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

    public static Book notNullBookById(BufferedReader reader) throws InstantiationException, IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
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

        // ввод нескольких авторов
        System.out.println("Введите имя автора:");
        String authorName = notNullInput(reader);
        Author author = new Author();
        if (authorService.findByName(authorName) == null) {
            author.setName(authorName);
            author.setDeleted(false);
            authorService.create(author);
        } else {
            author = authorService.findByName(authorName);
        }

        Book book = new Book();
        book.setName(name);
        bookService.create(book);

        BookAuthor relation = new BookAuthor();
        relation.setBookId(book.getId());
        relation.setAuthorId(author.getId());
        bookAuthorService.create(relation);
    }

    private void update(BufferedReader reader) throws InstantiationException, IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        System.out.println("Введите Id книги, которую хотите изменить:");
        Book book;
        book = notNullBookById(reader);
        if (book == null) {
            System.out.println("Id не был введен корректно");
            return;
        }
        String bookId = book.getId();
        String authorId;
        List<Author> authorsList = FromFile.findRelation(Author.class, book.getId());
        for (int i = 0; i < authorsList.size(); i++) {
            authorId = authorService.findByName(authorsList.get(i).getName()).getId();
            System.out.println(bookId + " " + authorId);
            bookAuthorService.delete(bookId, authorId);
        }

        System.out.println("Введите название книги:");
        String name = notNullInput(reader);

        // сделать ввод нескольких авторов
        System.out.println("Введите имя автора:");
        String authorName = notNullInput(reader);
        Author author = new Author();
        if (authorService.findByName(authorName) == null) {
            author.setName(authorName);
            author.setDeleted(false);
            authorService.create(author);
        } else {
            author = authorService.findByName(authorName);
        }

        book.setName(name);
        bookService.update(book);

        BookAuthor relation = new BookAuthor();
        relation.setBookId(book.getId());
        relation.setAuthorId(author.getId());
        bookAuthorService.create(relation);
    }

    private void delete(BufferedReader reader) throws InstantiationException, IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        System.out.println("Введите Id книги, которую хотите удалить:");
        Book book;
        book = notNullBookById(reader);
        if (book == null) {
            System.out.println("Id не был введен корректно");
            return;
        }

        String bookId = book.getId();
        String authorId;
        List<Author> authorsList = FromFile.findRelation(Author.class, book.getId());
        for (int i = 0; i < authorsList.size(); i++) {
            authorId = authorService.findByName(authorsList.get(i).getName()).getId();
            System.out.println(bookId + " " + authorId);
            bookAuthorService.delete(bookId, authorId);
        }

        boolean deleteResult = bookService.delete(book.getId());
        if (deleteResult) {
            System.out.println("Книга удалена!");
        }
    }

    private void findById(BufferedReader reader) throws InstantiationException, IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
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

    // тоже через релешен вытянуть
    private void findAllAuthorBooks(BufferedReader reader) throws InstantiationException, IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
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
        } catch (IOException e) {
            System.out.println("Error: = " + e.getMessage());
        }
    }

    public void bookOutput(Book book) throws NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<Author> authorsList = FromFile.findRelation(Author.class, book.getId());
        StringBuilder authors = new StringBuilder();
        for (Author author : authorsList) {
            authors.append(author.getName()).append(", ");
        }
        if (authors.substring(authors.length() - 2).equals(", "))
            authors.delete(authors.length() - 2, authors.length());

        separatingLine();
        System.out.println("Название       " + book.getName());
        System.out.println("Автор          " + authors);
        System.out.println("ID Книги       " + book.getId());
    }

    public static int parseIntWithoutExceptions(String number) {
        int defaultValue = Integer.MAX_VALUE;
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private void separatingLine() {
        System.out.println("──────────────────────────────────────────────");
    }
}
