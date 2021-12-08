package ua.com.alevel.persistence.dao;

import ua.com.alevel.db.BookDB;
import ua.com.alevel.persistence.entity.Author;
import ua.com.alevel.persistence.entity.Book;

public class BookDao {

    public void create(Book book) {
        BookDB.getInstance().create(book);
    }

    public void update(Book book) {
        BookDB.getInstance().update(book);
    }

    public boolean delete(String id) {
        return BookDB.getInstance().delete(id);
    }

    public Book findById(String id) {
        return BookDB.getInstance().findById(id);
    }

    public Book[] findAll() {
        return BookDB.getInstance().findAll();
    }

    public Book[] findAllAuthorBooks(Author author) {
        return BookDB.getInstance().findAllAuthorBooks(author);
    }
}
