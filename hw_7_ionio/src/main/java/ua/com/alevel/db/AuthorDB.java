package ua.com.alevel.db;

import ua.com.alevel.FromFile;
import ua.com.alevel.ToFile;
import ua.com.alevel.entity.Author;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class AuthorDB {

    private static AuthorDB instance;
    int size = 0;
    private Author[] authors;

    private AuthorDB() {
        authors = new Author[size];
    }

    public static AuthorDB getInstance() {
        if (instance == null) {
            instance = new AuthorDB();
        }
        return instance;
    }

    public void create(Author author) {
        author.setId(generateId());
        ToFile.create(author);
    }

    public void update(Author author) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        ToFile.update(author);
    }

    public void delete(String id) throws InstantiationException, IllegalAccessException {
        ToFile.delete(Author.class, id);
    }

    public Author findById(String id) throws NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        return FromFile.getObject(Author.class, id);
    }

    public List<Author> findAll() throws NoSuchFieldException, InstantiationException, IllegalAccessException {
        return FromFile.getAllObjects(Author.class);
    }

    public Author findByName(String name) throws NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        return FromFile.getObject(Author.class, name);
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        if (Arrays.stream(authors).anyMatch(author -> author.getId().equals(id))) {
            return generateId();
        }
        return id;
    }
}
