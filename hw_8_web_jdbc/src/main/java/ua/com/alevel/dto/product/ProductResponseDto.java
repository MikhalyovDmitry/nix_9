package ua.com.alevel.dto.product;

import ua.com.alevel.dto.ResponseDto;
import ua.com.alevel.entity.Product;

public class ProductResponseDto extends ResponseDto {

    private String name;
    private int price;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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
