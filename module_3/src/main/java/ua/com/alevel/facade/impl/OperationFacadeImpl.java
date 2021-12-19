package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.OperationFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Operation;
import ua.com.alevel.service.OperationService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.view.dto.request.OperationRequestDto;
import ua.com.alevel.view.dto.request.PageAndSizeData;
import ua.com.alevel.view.dto.request.SortData;
import ua.com.alevel.view.dto.response.OperationResponseDto;
import ua.com.alevel.view.dto.response.PageData;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperationFacadeImpl implements OperationFacade {

    private final OperationService operationService;

    public OperationFacadeImpl(OperationService operationService) {
        this.operationService = operationService;
    }

    @Override
    public OperationResponseDto create(OperationRequestDto operationRequestDto) {
        System.out.println(Double.parseDouble(operationRequestDto.getValue()));
        if (Double.parseDouble(operationRequestDto.getValue()) == 0)
            return null;
        Operation operation = new Operation();
        operation.setValue(operationRequestDto.getValue());
        operation.setName(operationRequestDto.getName());
        operation.setCategory(operationRequestDto.getCategory());
        operation.setAccountId(operationRequestDto.getAccountId());
        operation.setCreated(Timestamp.from(Instant.now()));
        operationService.create(operation);
        return new OperationResponseDto(operation);
    }

    @Override
    public void update(OperationRequestDto operationRequestDto, Long id) {
        Operation operation = operationService.findById(id);
        operation.setName(operationRequestDto.getName());
        operation.setValue(operationRequestDto.getValue());
        operation.setCategory(operationRequestDto.getCategory());
        operation.setAccountId(operationRequestDto.getAccountId());
        operationService.update(operation);
    }

    @Override
    public void delete(Long id) {
        operationService.delete(id);
    }

    @Override
    public OperationResponseDto findById(Long id) {
        Operation operation = operationService.findById(id);
        return new OperationResponseDto(operation);
    }

    @Override
    public PageData<OperationResponseDto> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setPageSize(pageAndSizeData.getSize());
        dataTableRequest.setCurrentPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());

        DataTableResponse<Operation> all = operationService.findAll(dataTableRequest);

        List<OperationResponseDto> list = all.getItems().
                stream().
                map(OperationResponseDto::new).
                collect(Collectors.toList());

        PageData<OperationResponseDto> pageData = new PageData<>();
        pageData.setItems(list);
        pageData.setCurrentPage(pageAndSizeData.getPage());
        pageData.setPageSize(pageAndSizeData.getSize());
        pageData.setOrder(sortData.getOrder());
        pageData.setSort(sortData.getSort());
        pageData.setItemsSize(all.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());

        return pageData;
    }
}
