package ua.com.alevel.persistence.entity;

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

    public void setBookId(String bookId) {
        this.bookId = bookId;
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
