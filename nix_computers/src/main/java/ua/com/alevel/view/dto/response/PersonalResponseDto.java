package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Order;
import ua.com.alevel.persistence.entity.Product;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.view.dto.ResponseDto;

import java.util.List;

public class PersonalResponseDto extends ResponseDto {

    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private List<Order> orders;
    private String fullName;

    public PersonalResponseDto(Personal personal) {
        if (personal != null) {
            this.email = personal.getEmail();
            this.firstName = personal.getFirstName();
            this.lastName = personal.getLastName();
            this.orders = personal.getOrders();
            this.address = personal.getAddress();
            super.setId(personal.getId());
        }
    }

    public double getUserTotal() {
        double userTotal = 0;
        for (Order order: this.orders) {
            userTotal = userTotal + order.getOrderTotalPrice();
        }
        return userTotal;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
