package ua.com.alevel.facade;

import ua.com.alevel.persistence.entity.User;
import ua.com.alevel.view.dto.request.UserRequestDto;
import ua.com.alevel.view.dto.response.AccountResponseDto;
import ua.com.alevel.view.dto.response.UserResponseDto;
import java.util.List;

public interface UserFacade extends BaseFacade<UserRequestDto, UserResponseDto> {

    List<AccountResponseDto> getAccounts(Long userId);

    void addAccount(Long userId, Long accountId);

    void removeAccount(Long userId, Long accountId);

}
