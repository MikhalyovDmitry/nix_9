package ua.com.alevel.dao;

import ua.com.alevel.ToFile;
import ua.com.alevel.db.BookDB;
import ua.com.alevel.entity.Book;

import java.util.List;

public class BookDao {

    public void create(Book book) {
        BookDB.getInstance().create(book);
    }

    public void update(Book book) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        BookDB.getInstance().update(book);
    }

    public boolean delete(String id) throws InstantiationException, IllegalAccessException {
        return BookDB.getInstance().delete(id);
    }

    public Book findById(String id) throws InstantiationException, IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        return BookDB.getInstance().findById(id);
    }

    public List<Book> findAll() throws NoSuchFieldException, InstantiationException, IllegalAccessException {
        return BookDB.getInstance().findAll();
    }

}
