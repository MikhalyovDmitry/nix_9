package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.PersonalFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Order;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.service.OrderService;
import ua.com.alevel.service.PersonalService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.view.dto.request.PageAndSizeData;
import ua.com.alevel.view.dto.request.PersonalRequestDto;
import ua.com.alevel.view.dto.request.SortData;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.PersonalResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonalFacadeImpl implements PersonalFacade {

    private final PersonalService personalService;
    private final OrderService orderService;

    public PersonalFacadeImpl(PersonalService personalService, OrderService orderService) {
        this.personalService = personalService;
        this.orderService = orderService;
    }

    @Override
    public void addOrderToUser(Long userId, Long orderId) {
        Personal personal = new Personal();
        personal = personalService.findById(userId).get();
        List<Order> orders = personal.getOrders();
        orders.add(orderService.findById(orderId).get());
        personal.setOrders(orders);
        personalService.update(personal);

    }

    @Override
    public Long findByName(String name) {
        return personalService.findByName(name);
    }

    @Override
    public void create(PersonalRequestDto personalRequestDto) {

    }

    @Override
    public void update(PersonalRequestDto personalRequestDto, Long id) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public PersonalResponseDto findById(Long id) {

        return new PersonalResponseDto(personalService.findById(id).get());
    }

    @Override
    public PageData<PersonalResponseDto> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setSize(pageAndSizeData.getSize());
        dataTableRequest.setPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());

        DataTableResponse<Personal> all = personalService.findAll(dataTableRequest);

        List<PersonalResponseDto> list = all.getItems().
                stream().
                map(PersonalResponseDto::new).
                collect(Collectors.toList());

        PageData<PersonalResponseDto> pageData = new PageData<>();
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
