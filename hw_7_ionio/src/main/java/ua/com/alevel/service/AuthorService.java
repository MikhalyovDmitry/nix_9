package ua.com.alevel.service;

import ua.com.alevel.FromFile;
import ua.com.alevel.persistence.dao.AuthorDao;
import ua.com.alevel.persistence.entity.Author;
import ua.com.alevel.persistence.entity.Book;
import java.util.List;

public class AuthorService {

    private final AuthorDao authorDao = new AuthorDao();

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
        return FromFile.findRelation(Book.class, author.getId());
    }

    public Author findByName(String name) throws NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        return authorDao.existByName(name);
    }
}
