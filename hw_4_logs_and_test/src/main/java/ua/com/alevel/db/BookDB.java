package ua.com.alevel.db;

import ua.com.alevel.persistence.entity.Author;
import ua.com.alevel.persistence.entity.Book;

import java.util.Arrays;
import java.util.UUID;

public class BookDB {

    private static BookDB instance;
    int size = 0;
    private Book[] books;

    private BookDB() {
        books = new Book[size];
    }

    public static BookDB getInstance() {
        if (instance == null) {
            instance = new BookDB();
        }
        return instance;
    }

    public void create(Book book) {
        Book[] booksImproved = new Book[size + 1];
        book.setId(generateId());
        if (size >= 0) System.arraycopy(books, 0, booksImproved, 0, size);
        size++;
        booksImproved[size - 1] = book;
        books = booksImproved;

    }

    public void update(Book book) {
        Book current = findById(book.getId());
        current.setName(book.getName());
        current.setAuthor(book.getAuthor());
    }

    public boolean delete(String id) {
        for (int i = 0; i < size; i++) {
            if (books[i].getId().equals(id)) {
                deleteAt(i);
                return true;
            }
        }
        return false;
    }

    private void deleteAt(int index) {
        Book[] booksImproved = new Book[size - 1];
        for (int i = 0, iD = 0; i < size; i++) {
            if (i != index) {
                booksImproved[iD] = books[i];
                iD++;
            }

        }
        size--;
        books = booksImproved;
    }

    public Book findById(String id) {
        return Arrays.stream(books)
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Book[] findAll() {
        return books;
    }

    public Book[] findAllAuthorBooks(Author author) {
        Book[] tempResult = new Book[books.length];
        int i = 0;
        for (Book book : books) {
            if (book.getAuthor().equals(author)) {
                tempResult[i] = book;
                i++;
            }
        }
        Book[] result = new Book[i];
        System.arraycopy(tempResult, 0, result, 0, result.length);
        return result;
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        if (Arrays.stream(books).anyMatch(book -> book.getId().equals(id))) {
            return generateId();
        }
        return id;
    }
}
