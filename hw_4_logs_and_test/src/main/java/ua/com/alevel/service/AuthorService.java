package ua.com.alevel.service;

import ua.com.alevel.dao.BookDao;
import ua.com.alevel.dao.AuthorDao;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

public class AuthorService {

    private final AuthorDao authorDao = new AuthorDao();
    private final BookDao bookDao = new BookDao();

    public Author create(Author author) {
        authorDao.create(author);
        return author;
    }

    public void update(Author author) {
        authorDao.update(author);
    }

    public boolean delete(String id) {
        return authorDao.delete(id);
    }

    public Author findById(String id) {
        return authorDao.findById(id);
    }

    public Author[] findAll() {
        return authorDao.findAll();
    }

    public Book[] findAllAuthorBooks(Author author) {
        return bookDao.findAllAuthorBooks(author);
    }

}
