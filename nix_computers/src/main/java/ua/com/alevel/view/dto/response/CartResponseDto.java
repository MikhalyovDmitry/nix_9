package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Cart;
import ua.com.alevel.view.dto.ResponseDto;

public class CartResponseDto extends ResponseDto {

    private Long userId;
    private Long productId;
//    private Long quantity;

    public CartResponseDto() {

    }

    public CartResponseDto(Cart cart) {
        super.setId(cart.getId());
        this.userId = cart.getUserId();
        this.productId = cart.getProductId();
//        this.quantity = cart.getQuantity();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

//    public Long getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(Long quantity) {
//        this.quantity = quantity;
//    }
}
