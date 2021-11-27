package ua.com.alevel.dao;

import ua.com.alevel.db.BookAuthorDB;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.BookAuthor;

public class BookAuthorDao {

    public void create(BookAuthor bookAuthor) {
        BookAuthorDB.getInstance().create(bookAuthor);
    }

    public void update(BookAuthor bookAuthor) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        BookAuthorDB.getInstance().update(bookAuthor);
    }

    public void delete(String bookId, String authorId) {
        BookAuthorDB.getInstance().delete(bookId, authorId);
    }

    public BookAuthor findById(String id) {
        return BookAuthorDB.getInstance().findById(id);
    }

    public BookAuthor[] findAll() {
        return BookAuthorDB.getInstance().findAll();
    }

//    public BookAuthor[] findAllAuthorBookAuthors(Author author) {
//        return BookAuthorDB.getInstance().findAllAuthorBookAuthors(author);
//    }
    
}
