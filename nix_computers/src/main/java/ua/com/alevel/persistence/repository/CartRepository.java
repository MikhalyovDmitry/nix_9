package ua.com.alevel.persistence.repository;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.Cart;

import java.util.List;

@Repository
public interface CartRepository extends AbstractRepository<Cart> {

    List<Cart> findCartIdByUserIdAndProductId(Long userId, Long productId);

}
