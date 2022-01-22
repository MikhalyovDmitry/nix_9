package ua.com.alevel.service;


import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.entity.Cart;
import ua.com.alevel.view.dto.response.CartResponseDto;

import java.util.List;

public interface CartService extends BaseService<Cart>{

    List<Cart> findCartIdByUserIdAndProductId(Long userId, Long productId);
}
