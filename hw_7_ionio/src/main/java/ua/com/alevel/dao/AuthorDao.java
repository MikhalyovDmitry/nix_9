package ua.com.alevel.dao;

import ua.com.alevel.db.AuthorDB;
import ua.com.alevel.entity.Author;

import java.util.List;

public class AuthorDao {

    public void create(Author author) {
        AuthorDB.getInstance().create(author);
    }

    public void update(Author author) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        AuthorDB.getInstance().update(author);
    }

    public void delete(String id) throws InstantiationException, IllegalAccessException {
        AuthorDB.getInstance().delete(id);
    }

    public Author findById(String id) throws NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        return AuthorDB.getInstance().findById(id);
    }

    public List<Author> findAll() throws NoSuchFieldException, InstantiationException, IllegalAccessException {
        return AuthorDB.getInstance().findAll();
    }

    public Author existByName(String name) throws NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        return AuthorDB.getInstance().findByName(name);
    }
}
