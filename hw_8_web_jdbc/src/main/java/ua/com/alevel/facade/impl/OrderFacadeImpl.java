package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.dto.order.OrderRequestDto;
import ua.com.alevel.dto.order.OrderResponseDto;
import ua.com.alevel.entity.Order;
import ua.com.alevel.facade.OrderFacade;
import ua.com.alevel.service.OrderService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderFacadeImpl implements OrderFacade {

    private final OrderService orderService;

    public OrderFacadeImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void create(OrderRequestDto orderRequestDto) {
        Order order = new Order();
        order.setCustomer(orderRequestDto.getCustomer());
        order.setDelivered(orderRequestDto.isDelivered());
        orderService.create(order);
    }

    @Override
    public void update(OrderRequestDto orderRequestDto, Long id) {
        Order order = orderService.findById(id);
        order.setCustomer(orderRequestDto.getCustomer());
        order.setDelivered(orderRequestDto.isDelivered());
        orderService.update(order);
    }

    @Override
    public void delete(Long id) {
        orderService.delete(id);
    }

    @Override
    public OrderResponseDto findById(Long id) {
        Order order = orderService.findById(id);
        return new OrderResponseDto(order);
    }


    @Override
    public List<OrderResponseDto> findAll() {
        return orderService.findAll()
                .stream()
                .map(OrderResponseDto::new)
                .collect(Collectors.toList());
    }
}
