package ua.com.alevel.facade;

import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.view.dto.request.PersonalRequestDto;
import ua.com.alevel.view.dto.response.PersonalResponseDto;

public interface PersonalFacade extends BaseFacade<PersonalRequestDto, PersonalResponseDto> {

    Personal findUserById(Long userId);

    void addOrderToUser(Long userId, Long orderId);

    void removeOrder(Long userId, Long orderId);

    Long findByName(String name);

}
