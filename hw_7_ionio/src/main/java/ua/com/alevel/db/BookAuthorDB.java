package ua.com.alevel.db;

import ua.com.alevel.ToFile;
import ua.com.alevel.entity.Book;
import ua.com.alevel.entity.BookAuthor;

import java.util.Arrays;
import java.util.UUID;

public class BookAuthorDB {

    private static BookAuthorDB instance;
    int size = 0;
    private BookAuthor[] bookAuthors;

    public BookAuthorDB() {
        bookAuthors = new BookAuthor[size];
    }

    public static BookAuthorDB getInstance() {
        if (instance == null) {
            instance = new BookAuthorDB();
        }
        return instance;
    }

    public void create(BookAuthor bookAuthor) {
        bookAuthor.setId(generateId());
        ToFile.create(bookAuthor);
    }

    public void update(BookAuthor bookAuthor) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        //ToFile.update(bookAuthor);
    }

    // тут надо подумать. специальный метод? или получится по айди?
    public void delete(String bookId, String authorId) {
        ToFile.delete(bookId, authorId);
    }

    public BookAuthor findById(String id) {
//        return Arrays.stream(bookAuthors)
//                .filter(u -> u.getId().equals(id))
//                .findFirst()
//                .orElse(null);
        return null;
    }

    public BookAuthor[] findAll() {
        return bookAuthors;
    }

//    public BookAuthor[] findAllAuthorBookAuthors(Author author) {
//        BookAuthor[] tempResult = new BookAuthor[bookAuthors.length];
//        int i = 0;
//        for (BookAuthor bookAuthor : bookAuthors) {
//            if (bookAuthor.getAuthor().equals(author)) {
//                tempResult[i] = bookAuthor;
//                i++;
//            }
//        }
//        BookAuthor[] result = new BookAuthor[i];
//        System.arraycopy(tempResult, 0, result, 0, result.length);
//        return result;
//    }


    public String generateId() {
        return UUID.randomUUID().toString();
    }




}
