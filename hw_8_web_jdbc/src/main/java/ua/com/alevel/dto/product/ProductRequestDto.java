package ua.com.alevel.dto.product;

import ua.com.alevel.dto.RequestDto;

public class ProductRequestDto extends RequestDto {

    private String name;
    private int price;
    private boolean inStock;

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
        return "ProductRequestDto{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", inStock=" + inStock +
                '}';
    }
}
