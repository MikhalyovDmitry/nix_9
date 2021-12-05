package ua.com.alevel.entity;


import java.util.Date;

public class Order extends BaseEntity {

    private String customer;
    private boolean delivered;
    private Date created;
    private Date updated;

    public Order() {
        super();
        this.created = new Date();
        this.updated = new Date();
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
}
