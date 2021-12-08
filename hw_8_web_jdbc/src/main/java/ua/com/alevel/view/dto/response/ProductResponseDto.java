package ua.com.alevel.view.dto.response;

import ua.com.alevel.view.dto.ResponseDto;
import ua.com.alevel.persistence.entity.Product;

public class ProductResponseDto extends ResponseDto {

    private String name;
    private double price;
    private boolean inStock;

    public ProductResponseDto() {
    }

    public ProductResponseDto(Product product) {
        if (product != null) {
            super.setId(product.getId());
            this.name = product.getName();
            this.price = product.getPrice();
            this.inStock = product.isInStock();
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    @Override
    public String toString() {
        return "ProductResponseDto{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", inStock=" + inStock +
                '}';
    }
}
