package ua.com.alevel.service;

import ua.com.alevel.dao.AuthorDao;
import ua.com.alevel.dao.BookAuthorDao;
import ua.com.alevel.db.BookAuthorDB;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.BookAuthor;

import java.util.ArrayList;
import java.util.List;

public class BookAuthorService {

    private final BookAuthorDao bookAuthorDao = new BookAuthorDao();
    private final AuthorDao authorDao = new AuthorDao();

    public void create(BookAuthor bookAuthor) {
        bookAuthorDao.create(bookAuthor);
    }

    public void update(BookAuthor bookAuthor) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        bookAuthorDao.update(bookAuthor);
    }

    public void delete(String bookId, String authorId) {
        bookAuthorDao.delete(bookId, authorId);
    }

    public BookAuthor findById(String id) {
        return bookAuthorDao.findById(id);
    }

    public BookAuthor[] findAll() {
        return bookAuthorDao.findAll();
    }

    public List<Author> findAuthorByBookId(String id) throws NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<Author> result = new ArrayList<>();
        BookAuthor[] array = bookAuthorDao.findAll();
        for (BookAuthor bookAuthor : array) {
            if (bookAuthor.getBookId().equals(id)) {
                result.add(authorDao.findById(bookAuthor.getAuthorId()));
            }
        }
        return result;
    }
    
}
