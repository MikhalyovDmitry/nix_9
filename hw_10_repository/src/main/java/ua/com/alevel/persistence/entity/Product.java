package ua.com.alevel.persistence.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    private String name;
    private double price;
    private boolean inStock;

    @ManyToMany(mappedBy = "products", cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE })
    private List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Product() {
        super();
        this.orders = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
