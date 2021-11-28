package ua.com.alevel.db;

import ua.com.alevel.FromFile;
import ua.com.alevel.ToFile;
import ua.com.alevel.entity.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// избавиться от GetInstance  и перенести в ДАО слой
// генератор ИД тоже куда-нибудь
public class BookDB {

    private static BookDB instance;
    private final List<Book> books;

    private BookDB() {
        books = new ArrayList<>();
    }

    public static BookDB getInstance() {
        if (instance == null) {
            instance = new BookDB();
        }
        return instance;
    }

    public void create(Book book) {
        book.setId(generateId());
        ToFile.create(book);
    }

    public void update(Book book) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        ToFile.update(book);
    }

    public boolean delete(String id) throws InstantiationException, IllegalAccessException {
        ToFile.delete(Book.class, id);
        return true;
    }

    public Book findById(String id) throws InstantiationException, IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        return FromFile.getObject(Book.class, id);
    }

    public List<Book> findAll() throws NoSuchFieldException, InstantiationException, IllegalAccessException {
        return FromFile.getAllObjects(Book.class);
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return generateId();
            }
        }
        return id;
    }
}
