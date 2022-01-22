package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Cart;
import ua.com.alevel.persistence.repository.CartRepository;
import ua.com.alevel.service.CartService;
import ua.com.alevel.view.dto.response.CartResponseDto;

import java.util.List;
import java.util.Optional;


@Service
public class CartServiceImpl implements CartService {

    private final CrudRepositoryHelper<Cart, CartRepository> cartRepositoryHelper;
    private final CartRepository cartRepository;

    public CartServiceImpl(CrudRepositoryHelper<Cart, CartRepository> cartRepositoryHelper, CartRepository cartRepository) {
        this.cartRepositoryHelper = cartRepositoryHelper;
        this.cartRepository = cartRepository;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void create(Cart entity) {
        cartRepositoryHelper.create(cartRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void update(Cart entity) {
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void delete(Long id) {
        cartRepositoryHelper.delete(cartRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cart> findById(Long id) {

        return cartRepositoryHelper.findById(cartRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Cart> findAll(DataTableRequest dataTableRequest) {

        return cartRepositoryHelper.findAll(cartRepository, dataTableRequest);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cart> findCartIdByUserIdAndProductId(Long userId, Long productId) {
        return cartRepository.findCartIdByUserIdAndProductId(userId, productId);
    }


}
