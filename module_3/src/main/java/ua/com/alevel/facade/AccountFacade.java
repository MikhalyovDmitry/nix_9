package ua.com.alevel.facade;

import ua.com.alevel.persistence.entity.Operation;
import ua.com.alevel.view.dto.request.AccountRequestDto;
import ua.com.alevel.view.dto.response.AccountResponseDto;
import ua.com.alevel.view.dto.response.OperationResponseDto;

import java.util.List;

public interface AccountFacade extends BaseFacade<AccountRequestDto, AccountResponseDto>{

    List<OperationResponseDto> getOperations(Long accountId);

    void addOperation(Long accountId, Long operationId);

    void removeOperation(Long accountId, Long operationId);

}
