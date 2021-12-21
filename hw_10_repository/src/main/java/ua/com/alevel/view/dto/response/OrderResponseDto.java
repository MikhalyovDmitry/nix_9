package ua.com.alevel.view.dto.response;

import ua.com.alevel.view.dto.ResponseDto;
import ua.com.alevel.persistence.entity.Order;

import java.util.Date;

public class OrderResponseDto extends ResponseDto {

    private String customer;
    private boolean delivered;
    private Date created;
    private Date updated;

    public OrderResponseDto() {
    }

    public OrderResponseDto(Order order) {
        super.setId(order.getId());
        this.customer = order.getCustomer();
        this.delivered = order.isDelivered();
        this.created = order.getCreated();
        this.updated = order.getUpdated();
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
