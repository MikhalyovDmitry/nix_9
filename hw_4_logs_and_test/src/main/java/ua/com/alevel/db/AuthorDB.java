package ua.com.alevel.db;

import ua.com.alevel.persistence.entity.Author;

import java.util.Arrays;
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
        Author[] authorsImproved = new Author[size + 1];
        author.setId(generateId());
        if (size >= 0) System.arraycopy(authors, 0, authorsImproved, 0, size);
        size++;
        authorsImproved[size - 1] = author;
        authors = authorsImproved;

    }

    public void update(Author author) {
        Author current = findById(author.getId());
        current.setName(author.getName());
        current.setGenre(author.getGenre());
    }

    public void delete(String id) {
        for (int i = 0; i < size; i++) {
            if (authors[i].getId().equals(id)) {
                deleteAt(i);
                return;
            }
        }
    }

    private void deleteAt(int index) {
        Author[] authorsImproved = new Author[size - 1];
        for (int i = 0, iD = 0; i < size; i++) {
            if (i != index) {
                authorsImproved[iD] = authors[i];
                iD++;
            }

        }
        size--;
        authors = authorsImproved;
    }

    public Author findById(String id) {
        return Arrays.stream(authors)
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Author[] findAll() {
        return authors;
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        if (Arrays.stream(authors).anyMatch(author -> author.getId().equals(id))) {
            return generateId();
        }
        return id;
    }
}
