package ua.com.alevel.persistence.entity;

public class Author {

    private String name;
    private String genre;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
