package ua.com.alevel.view.dto.request;

import ua.com.alevel.persistence.entity.Order;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.view.dto.RequestDto;

import java.util.List;

public class PersonalRequestDto extends RequestDto {

    private String email;
    private String phone;
    private String address;
    private String firstName;
    private String lastName;
    private List<Order> orders;

    public PersonalRequestDto(Personal personal) {
        if (personal != null) {
            this.email = personal.getEmail();
            this.phone = personal.getPhone();
            this.address = personal.getAddress();
            this.firstName = personal.getFirstName();
            this.lastName = personal.getLastName();
            this.orders = personal.getOrders();
        }
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
