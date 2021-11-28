package ua.com.alevel.service;

import ua.com.alevel.dao.BookAuthorDao;
import ua.com.alevel.entity.BookAuthor;

public class BookAuthorService {

    private final BookAuthorDao bookAuthorDao = new BookAuthorDao();

    public void create(BookAuthor bookAuthor) {
        bookAuthorDao.create(bookAuthor);
    }

    public void delete(String bookId, String authorId) {
        bookAuthorDao.delete(bookId, authorId);
    }
}
