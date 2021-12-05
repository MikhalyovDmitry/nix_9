package ua.com.alevel.dto.order;

import ua.com.alevel.dto.RequestDto;

public class OrderRequestDto extends RequestDto {

    private String customer;
    private boolean delivered;

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
