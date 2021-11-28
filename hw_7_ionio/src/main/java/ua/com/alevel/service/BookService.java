package ua.com.alevel.service;

import ua.com.alevel.dao.BookDao;
import ua.com.alevel.entity.Book;
import java.util.List;

public class BookService {

    private final BookDao bookDao = new BookDao();

    public void create(Book book) {
        bookDao.create(book);
    }

    public void update(Book book) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        bookDao.update(book);
    }

    public boolean delete(String id) throws InstantiationException, IllegalAccessException {
        return bookDao.delete(id);
    }

    public Book findById(String id) throws InstantiationException, IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        return bookDao.findById(id);
    }

    public List<Book> findAll() throws NoSuchFieldException, InstantiationException, IllegalAccessException {
        return bookDao.findAll();
    }


}
