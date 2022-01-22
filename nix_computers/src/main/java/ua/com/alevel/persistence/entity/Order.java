package ua.com.alevel.persistence.entity;


import java.util.*;
import javax.persistence.*;


@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    private String customer;
    private boolean delivered;
    private String email;
    private String phone;
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    public Order() {
        super();
        this.created = new Date();
        this.updated = new Date();
        products = new ArrayList<>();
    }

    public double getOrderTotalPrice() {
        double totalPrice = 0;
        for (Product product: this.products) {
            totalPrice = totalPrice + product.getPrice();
        }
        return totalPrice;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        products.add(product);
        product.getOrders().add(this);
    }

    public void removeProduct(Product product) {
        products.remove(product);
        product.getOrders().remove(this);
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
