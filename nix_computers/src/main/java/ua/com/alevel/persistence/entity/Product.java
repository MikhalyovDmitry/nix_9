package ua.com.alevel.persistence.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    private String name;
    private double price;
    private boolean inStock;

    private String image;

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

    public int getSales() {
        int sales;
        List<Order> orders = this.getOrders();
        sales = (int) orders.
                stream().
                map(Order::getProducts).
                flatMap(Collection::stream).
                filter(product -> Objects.equals(product.getId(), this.getId())).
                count();
        return sales;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
