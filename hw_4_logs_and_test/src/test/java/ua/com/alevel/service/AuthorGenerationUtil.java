package ua.com.alevel.service;

import ua.com.alevel.entity.Author;

public class AuthorGenerationUtil {

    public static final String GENRE = "test";
    public static final String NAME = "test";

    public static Author generateAuthor() {
        Author Author = new Author();
        Author.setName(NAME);
        Author.setGenre(GENRE);
        return Author;
    }

    public static Author generateAuthor(String name, String genre) {
        Author Author = new Author();
        Author.setName(name);
        Author.setGenre(genre);
        return Author;
    }

    public static Author generateAuthor(String genre) {
        Author Author = new Author();
        Author.setName(NAME);
        Author.setGenre(genre);
        return Author;
    }
}