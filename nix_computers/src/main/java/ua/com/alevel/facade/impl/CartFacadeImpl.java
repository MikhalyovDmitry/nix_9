package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.CartFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.Cart;
import ua.com.alevel.service.CartService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.view.dto.request.CartRequestDto;
import ua.com.alevel.view.dto.request.PageAndSizeData;
import ua.com.alevel.view.dto.request.SortData;
import ua.com.alevel.view.dto.response.CartResponseDto;
import ua.com.alevel.view.dto.response.PageData;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CartFacadeImpl implements CartFacade {

    private final CartService cartService;

    public CartFacadeImpl(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public void deleteCartByUsersId(Long userId) {
        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setSize(Integer.MAX_VALUE);
        dataTableRequest.setPage(1);
        dataTableRequest.setSort("id");
        dataTableRequest.setOrder("asc");
        List<Cart> carts = cartService.findAll(dataTableRequest).getItems();

       for (Cart cart: carts) {
            if (cart.getUserId().equals(userId)) {
                cartService.delete(cart.getId());
            }
        }
    }

    @Override
    public void create(CartRequestDto cartRequestDto) {
        Cart cart = new Cart();
        cart.setUserId(cartRequestDto.getUserId());
        cart.setProductId(cartRequestDto.getProductId());
//        cart.setQuantity(cartRequestDto.getQuantity());
        cartService.create(cart);

    }

    @Override
    public void update(CartRequestDto cartRequestDto, Long id) {

    }

    @Override
    public void delete(Long id) {
        cartService.delete(id);
    }

    @Override
    public CartResponseDto findById(Long id) {
        return null;
    }

    @Override
    public PageData<CartResponseDto> findAll(WebRequest request) {

        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setSize(Integer.MAX_VALUE);
        dataTableRequest.setPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());

        DataTableResponse<Cart> all = cartService.findAll(dataTableRequest);

        List<CartResponseDto> list = all.getItems().
                stream().
                map(CartResponseDto::new).
                collect(Collectors.toList());

        PageData<CartResponseDto> pageData = new PageData<>();
        pageData.setItems(list);
        pageData.setCurrentPage(pageAndSizeData.getPage());
        pageData.setPageSize(pageAndSizeData.getSize());
        pageData.setOrder(sortData.getOrder());
        pageData.setSort(sortData.getSort());
        pageData.setItemsSize(all.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());

        return pageData;

    }

    @Override
    public int cartCountByUserId(Long userId, WebRequest webRequest) {
        int count;
        List<CartResponseDto> list = findAll(webRequest).getItems();
        count = (int) list.stream().filter(dto -> Objects.equals(dto.getUserId(), userId)).count();
        return count;
    }

    @Override
    public List<CartResponseDto> cartByUserId(Long userId, WebRequest request) {
        return findAll(request)
                .getItems().stream()
                .filter(dto -> Objects.equals(dto.getUserId(), userId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Long> findCartIdByUserIdAndProductId(Long userId, Long productId) {
        List<Cart> carts = cartService.findCartIdByUserIdAndProductId(userId, productId);
        List<Long> cartIds = carts.stream().map(BaseEntity::getId).collect(Collectors.toList());
        return cartIds;
    }
}
