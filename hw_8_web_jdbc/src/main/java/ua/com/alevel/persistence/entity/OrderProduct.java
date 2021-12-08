package ua.com.alevel.persistence.entity;

public class OrderProduct extends BaseEntity {

    private Long orderId;
    private Long productId;

    public OrderProduct() {
        super();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "OrderProduct{ ID=" + super.getId() +
                "orderId=" + orderId +
                ", productId=" + productId +
                '}';
    }
}
