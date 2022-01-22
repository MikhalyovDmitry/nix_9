package ua.com.alevel.persistence.entity.user;

import ua.com.alevel.persistence.entity.Order;
import ua.com.alevel.persistence.entity.Product;
import ua.com.alevel.persistence.type.RoleType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue("PERSONAL")
public class Personal extends User {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "birth_day")
    private Date birthDay;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Transient
    private String fullName;

    @Transient
    private Integer age;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "user_order",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<Order> orders;

    public Personal() {
        super();
        setRoleType(RoleType.ROLE_PERSONAL);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
