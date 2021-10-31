package ua.com.alevel.service;

import org.junit.jupiter.api.*;
import ua.com.alevel.entity.Author;

import java.util.Arrays;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthorServiceTest {

    private final static AuthorService authorService = new AuthorService();
    private final static int AUTHORS_SIZE = 10;
    private final static String TEST = "Test";

    @BeforeAll
    public static void setUp() {
        for (int i = 0; i < AUTHORS_SIZE; i++) {
            Author author = AuthorGenerationUtil.generateAuthor(AuthorGenerationUtil.NAME + i, Integer.toString(i));
            authorService.create(author);
        }
        Assertions.assertEquals(AUTHORS_SIZE, authorService.findAll().length);
    }

    @Order(1)
    @Test
    public void shouldBeCreateAuthor() {
        Author author = new Author();
        author.setName(TEST);
        author.setGenre(TEST);
        authorService.create(author);
        verifyAuthorListWhenAuthorsListIsNotEmpty(AUTHORS_SIZE + 1);
    }

    @Order(2)
    @Test
    public void shouldBeDeleteAuthor() {
        String id = getRandomIdFromAuthorList();
        authorService.delete(id);
        verifyAuthorListWhenAuthorsListIsNotEmpty(AUTHORS_SIZE);
    }

    @Order(3)
    @Test
    public void shouldBeFindAuthorWhenIdIsRandom() {
        Author author = getRandomAuthorFromAuthorList(getRandomIdFromAuthorList());
        Assertions.assertNotNull(author);
    }

    @Order(4)
    @Test
    public void shouldBeUpdateAuthor() {
        String id = getRandomIdFromAuthorList();
        Author author = getRandomAuthorFromAuthorList(id);
        author.setGenre(TEST);
        author.setName(TEST);
        authorService.update(author);
        author = authorService.findById(id);
        Assertions.assertEquals(TEST, author.getName());
        Assertions.assertEquals(TEST, author.getGenre());
    }

    private void verifyAuthorListWhenAuthorsListIsNotEmpty(int size) {
        Assertions.assertEquals(size, authorService.findAll().length);
    }

    private String getRandomIdFromAuthorList() {
        return Arrays.stream(authorService.findAll()).findFirst().get().getId();
    }

    private Author getRandomAuthorFromAuthorList(String id) {
        return authorService.findById(id);
    }
}
