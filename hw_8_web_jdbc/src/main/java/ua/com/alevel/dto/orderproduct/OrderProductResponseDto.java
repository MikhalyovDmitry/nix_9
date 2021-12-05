package ua.com.alevel.dto.orderproduct;

import ua.com.alevel.dto.ResponseDto;
import ua.com.alevel.entity.OrderProduct;

public class OrderProductResponseDto extends ResponseDto {

    private Long orderId;
    private Long productId;

    public OrderProductResponseDto() {
    }

    public OrderProductResponseDto(OrderProduct orderProduct) {
        if (orderProduct != null) {
            super.setId(orderProduct.getId());
            this.orderId = orderProduct.getOrderId();
            this.productId = orderProduct.getProductId();
        }
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
}
