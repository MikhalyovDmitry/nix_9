package ua.com.alevel.dao;

import ua.com.alevel.db.BookDB;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

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
