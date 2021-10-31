package ua.com.alevel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dao.BookDao;
import ua.com.alevel.dao.AuthorDao;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

public class AuthorService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private final AuthorDao authorDao = new AuthorDao();
    private final BookDao bookDao = new BookDao();

    public void create(Author author) {
        LOGGER_INFO.info("Start creating author");
        authorDao.create(author);
        LOGGER_INFO.info("Finish creating author");

    }

    public void update(Author author) {
        LOGGER_WARN.warn("Start updating author");
        authorDao.update(author);
        LOGGER_WARN.warn("Finish updating author");
    }

    public void delete(String id) {
        LOGGER_WARN.warn("Start deleting author");
        authorDao.delete(id);
        LOGGER_WARN.warn("Finish deleting author");
    }

    public Author findById(String id) {
        try {
            return authorDao.findById(id);
        } catch (RuntimeException e) {
            LOGGER_ERROR.error("Author not found by id: " + id);
        }
        return authorDao.findById(id);

    }

    public Author[] findAll() {
        return authorDao.findAll();
    }

    public Book[] findAllAuthorBooks(Author author) {
        return bookDao.findAllAuthorBooks(author);
    }

}
