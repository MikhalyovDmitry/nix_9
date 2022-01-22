package ua.com.alevel.facade;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.view.dto.request.CartRequestDto;
import ua.com.alevel.view.dto.response.CartResponseDto;

import java.util.List;

public interface CartFacade extends BaseFacade<CartRequestDto, CartResponseDto> {

    void deleteCartByUsersId(Long userId);

    int cartCountByUserId(Long userId, WebRequest webRequest);

    List<CartResponseDto> cartByUserId(Long userId, WebRequest request);

    List<Long> findCartIdByUserIdAndProductId(Long userId, Long productId);
}
