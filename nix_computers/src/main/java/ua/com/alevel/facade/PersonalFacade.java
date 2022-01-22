package ua.com.alevel.facade;

import ua.com.alevel.view.dto.request.PersonalRequestDto;
import ua.com.alevel.view.dto.response.PersonalResponseDto;

public interface PersonalFacade extends BaseFacade<PersonalRequestDto, PersonalResponseDto> {


    void addOrderToUser(Long userId, Long orderId);

    Long findByName(String name);
}
