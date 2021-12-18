package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.OrderProduct;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.view.dto.request.PageAndSizeData;
import ua.com.alevel.view.dto.request.ProductRequestDto;
import ua.com.alevel.view.dto.request.SortData;
import ua.com.alevel.view.dto.response.OrderProductResponseDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.ProductResponseDto;
import ua.com.alevel.persistence.entity.Product;
import ua.com.alevel.facade.ProductFacade;
import ua.com.alevel.service.OrderService;
import ua.com.alevel.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductFacadeImpl implements ProductFacade {

    private final OrderService orderService;
    private final ProductService productService;

    public ProductFacadeImpl(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    @Override
    public void create(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setName(productRequestDto.getName());
        product.setPrice(productRequestDto.getPrice());
        product.setInStock(productRequestDto.isInStock());
        productService.create(product);
    }

    @Override
    public void update(ProductRequestDto productRequestDto, Long id) {
        Product product = productService.findById(id);
        product.setName(productRequestDto.getName());
        product.setPrice(productRequestDto.getPrice());
        product.setInStock(productRequestDto.isInStock());
        productService.update(product);
    }

    @Override
    public void delete(Long id) {
        productService.delete(id);
    }

    @Override
    public ProductResponseDto findById(Long id) {
        return new ProductResponseDto(productService.findById(id));
    }

    @Override
    public PageData<ProductResponseDto> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setPageSize(pageAndSizeData.getSize());
        dataTableRequest.setCurrentPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());

        DataTableResponse<Product> all = productService.findAll(dataTableRequest);

        List<ProductResponseDto> list = all.getItems().
                stream().
                map(ProductResponseDto::new).
                collect(Collectors.toList());

        PageData<ProductResponseDto> pageData = new PageData<>();
        pageData.setItems(list);
        pageData.setCurrentPage(pageAndSizeData.getPage());
        pageData.setPageSize(pageAndSizeData.getSize());
        pageData.setOrder(sortData.getOrder());
        pageData.setSort(sortData.getSort());
        pageData.setItemsSize(all.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    private List<ProductResponseDto> convertToDtoByEntity(List<Product> products) {
        return products.stream()
                .map(ProductResponseDto::new)
                .collect(Collectors.toList());
    }
}
