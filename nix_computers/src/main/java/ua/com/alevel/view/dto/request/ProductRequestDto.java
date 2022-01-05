package ua.com.alevel.view.dto.request;

import ua.com.alevel.view.dto.RequestDto;

public class ProductRequestDto extends RequestDto {

    private String name;
    private double price;
    private boolean inStock;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
        return "ProductRequestDto{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", inStock=" + inStock +
                '}';
    }
}
