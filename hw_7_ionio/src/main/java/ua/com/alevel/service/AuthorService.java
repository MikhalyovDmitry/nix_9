package ua.com.alevel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dao.AuthorDao;
import ua.com.alevel.dao.BookAuthorDao;
import ua.com.alevel.dao.BookDao;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.entity.BookAuthor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AuthorService {


    private final AuthorDao authorDao = new AuthorDao();
    private final BookAuthorDao bookAuthorDao = new BookAuthorDao();
    private final BookDao bookDao = new BookDao();

    public void create(Author author) {
        authorDao.create(author);
    }

    public void update(Author author) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        authorDao.update(author);
    }

    public void delete(String id) throws InstantiationException, IllegalAccessException {
        authorDao.delete(id);
    }

    public Author findById(String id) throws NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        return authorDao.findById(id);
    }

    public List<Author> findAll() throws NoSuchFieldException, InstantiationException, IllegalAccessException {
        return authorDao.findAll();
    }

    public List<Book> findAllAuthorBooks(Author author) throws InstantiationException, IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        List<Book> result = new ArrayList<>();
        BookAuthor[] ba = bookAuthorDao.findAll();
        for (int i = 0; i < ba.length; i++) {
            if (ba[i].getAuthorId().equals(author.getId())) {
                System.out.println("ba[i].getAuthorId() = " + ba[i].getAuthorId() + " author.getId() = " + author.getId());
                result.add(bookDao.findById(ba[i].getBookId()));
            }
        }
        return result;
    }

    public Author findByName(String name) throws NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        return authorDao.existByName(name);
    }

}
