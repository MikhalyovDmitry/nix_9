package ua.com.alevel.persistence.dao;

import ua.com.alevel.db.BookAuthorDB;
import ua.com.alevel.persistence.entity.BookAuthor;

public class BookAuthorDao {

    public void create(BookAuthor bookAuthor) {
        BookAuthorDB.getInstance().create(bookAuthor);
    }

    public void delete(String bookId, String authorId) {
        BookAuthorDB.getInstance().delete(bookId, authorId);
    }
}
