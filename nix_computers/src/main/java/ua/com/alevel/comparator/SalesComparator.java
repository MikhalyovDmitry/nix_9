package ua.com.alevel.comparator;

import ua.com.alevel.facade.ProductFacade;
import ua.com.alevel.persistence.entity.Product;
import ua.com.alevel.view.dto.response.ProductResponseDto;

import java.util.Comparator;

public class SalesComparator implements Comparator<ProductResponseDto> {

    public SalesComparator(ProductFacade productFacade) {
    }

    @Override
    public int compare(ProductResponseDto p1, ProductResponseDto p2) {

        if (p1.getSales() == p2.getSales()) {
            return 0;
        }
        if (p1.getSales() < p2.getSales()) {
            return 1;
        }
        else {
            return -1;
        }
    }
}
