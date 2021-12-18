package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.AccountFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.Operation;
import ua.com.alevel.service.AccountService;
import ua.com.alevel.service.OperationService;
import ua.com.alevel.service.UserService;
import ua.com.alevel.type.Category;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.view.dto.request.AccountRequestDto;
import ua.com.alevel.view.dto.request.PageAndSizeData;
import ua.com.alevel.view.dto.request.SortData;
import ua.com.alevel.view.dto.response.AccountResponseDto;
import ua.com.alevel.view.dto.response.OperationResponseDto;
import ua.com.alevel.view.dto.response.PageData;

import java.util.*;

@Service
public class AccountFacadeImpl implements AccountFacade {

    private final AccountService accountService;
    private final UserService userService;
    private final OperationService operationService;

    public AccountFacadeImpl(AccountService accountService, UserService userService, OperationService operationService) {
        this.accountService = accountService;
        this.userService = userService;
        this.operationService = operationService;
    }

    @Override
    public AccountResponseDto create(AccountRequestDto accountRequestDto) {
        Account account = new Account();
        account.setBalance(0L);
        account.setCreated(new Date());
        account.setUpdated(new Date());
        account.setUserId(accountRequestDto.getUserId());
        accountService.create(account);
        return new AccountResponseDto(account);
    }

    @Override
    public void update(AccountRequestDto accountRequestDto, Long id) {
        Account account = accountService.findById(id);
        account.setBalance(accountRequestDto.getBalance());
        account.setCreated(accountRequestDto.getCreated());
        account.setUserId(accountRequestDto.getUserId());
        account.setUpdated(new Date());
        accountService.update(account);
    }

    @Override
    public void delete(Long id) {
        accountService.delete(id);
    }

    @Override
    public AccountResponseDto findById(Long id) {
        Account account = accountService.findById(id);
        AccountResponseDto dto = new AccountResponseDto(account);
        dto.setUser(userService.findById(account.getUserId()).getName());
        return dto;
    }

    @Override
    public PageData<AccountResponseDto> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setPageSize(pageAndSizeData.getSize());
        dataTableRequest.setCurrentPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());

        DataTableResponse<Account> all = accountService.findAll(dataTableRequest);

        List<AccountResponseDto> list = new ArrayList<>();
        for (Account account : all.getItems()) {
            AccountResponseDto accountResponseDto = new AccountResponseDto(account);
            accountResponseDto.setUser(userService.findById(account.getUserId()).getName());
            list.add(accountResponseDto);
        }

        PageData<AccountResponseDto> pageData = new PageData<>();
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
    public List<OperationResponseDto> getOperations(Long accountId) {
        List<Operation> operations = accountService.getOperations(accountId);
        List<OperationResponseDto> list = new ArrayList<>();
        for (Operation operation : operations) {
            OperationResponseDto operationResponseDto = new OperationResponseDto(operation);
            list.add(operationResponseDto);
        }
        return list;
    }

    @Override
    public void addOperation(Long accountId, Long operationId) {
        Account account = accountService.findById(accountId);
        Operation operation = operationService.findById(operationId);
        Category category = operation.getCategory();
        switch (category) {
            case INCOME -> account.setBalance(account.getBalance() + operation.getValue());
            case EXPENSE -> account.setBalance(account.getBalance() - operation.getValue());
        }
        accountService.update(account);
        accountService.addOperation(accountId, operationId);
    }

    @Override
    public void removeOperation(Long accountId, Long operationId) {
        accountService.removeOperation(accountId, operationId);
    }
}
