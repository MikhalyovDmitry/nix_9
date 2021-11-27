package ua.com.alevel.entity;

public class Author {

    public String name;
    public String id;
    public boolean deleted;

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                ", id='" + id + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
