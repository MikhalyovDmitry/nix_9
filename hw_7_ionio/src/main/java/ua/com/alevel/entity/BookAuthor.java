package ua.com.alevel.entity;

public class BookAuthor {

    public String id;
    public String bookId;
    public String authorId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "BookAuthor{" +
                ", bookId='" + bookId + '\'' +
                ", authorId='" + authorId + '\'' +
                '}';
    }
}
