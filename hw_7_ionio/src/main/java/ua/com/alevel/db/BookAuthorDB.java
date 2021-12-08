package ua.com.alevel.db;

import ua.com.alevel.ToFile;
import ua.com.alevel.persistence.entity.BookAuthor;
import java.util.UUID;

public class BookAuthorDB {

    private static BookAuthorDB instance;
    int size = 0;
    private BookAuthor[] bookAuthors;
    public BookAuthorDB() {
        bookAuthors = new BookAuthor[size];
    }

    public static BookAuthorDB getInstance() {
        if (instance == null) {
            instance = new BookAuthorDB();
        }
        return instance;
    }

    public void create(BookAuthor bookAuthor) {
        bookAuthor.setId(generateId());
        ToFile.create(bookAuthor);
    }

    public void delete(String bookId, String authorId) {
        ToFile.delete(bookId, authorId);
    }

    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
