package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Product;
import ua.com.alevel.view.dto.ResponseDto;
import ua.com.alevel.persistence.entity.Order;

import java.util.Date;
import java.util.List;

public class OrderResponseDto extends ResponseDto {

    private String customer;
    private boolean delivered;
    private Date created;
    private Date updated;
    private String email;
    private String phone;
    private String name;
    private List<Product> products;

    public OrderResponseDto() {
    }

    public OrderResponseDto(Order order) {
        super.setId(order.getId());
        this.customer = order.getCustomer();
        this.delivered = order.isDelivered();
        this.created = order.getCreated();
        this.updated = order.getUpdated();
        this.email = order.getEmail();
        this.phone = order.getPhone();
        this.name = order.getName();
        this.products = order.getProducts();
    }

    public double getTotal() {
        double total = 0;
        for (Product product: this.products) {
            total = total + product.getPrice();
        }
        return total;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
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

}
