package ua.com.alevel.view.dto.request;

import ua.com.alevel.persistence.entity.Product;
import ua.com.alevel.view.dto.RequestDto;

import java.util.List;

public class OrderRequestDto extends RequestDto {

    private String customer;
    private boolean delivered;
    private String email;
    private String phone;
    private String name;
    private List<Product> products;

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
}
