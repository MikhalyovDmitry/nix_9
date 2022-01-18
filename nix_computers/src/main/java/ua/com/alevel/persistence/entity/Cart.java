package ua.com.alevel.persistence.entity;

import java.util.*;
import javax.persistence.*;


@Entity
@Table(name = "carts")
public class Cart extends BaseEntity {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "product_id")
    private Long productId;

//    private Long quantity;

    public Cart() {
        super();
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
