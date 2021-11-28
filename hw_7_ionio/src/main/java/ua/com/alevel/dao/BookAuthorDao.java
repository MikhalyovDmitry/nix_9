package ua.com.alevel.dao;

import ua.com.alevel.db.BookAuthorDB;
import ua.com.alevel.entity.BookAuthor;

public class BookAuthorDao {

    public void create(BookAuthor bookAuthor) {
        BookAuthorDB.getInstance().create(bookAuthor);
    }

    public void delete(String bookId, String authorId) {
        BookAuthorDB.getInstance().delete(bookId, authorId);
    }
}
