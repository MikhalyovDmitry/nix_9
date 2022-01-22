package ua.com.alevel.view.dto.response;

import ua.com.alevel.view.dto.ResponseDto;
import ua.com.alevel.persistence.entity.Product;

import java.math.BigDecimal;

public class ProductResponseDto extends ResponseDto {

    private String name;
    private BigDecimal price;
    private boolean inStock;
    private String image;

    public ProductResponseDto() {
    }

    public ProductResponseDto(Product product) {
        if (product != null) {
            super.setId(product.getId());
            this.name = product.getName();
            this.price = BigDecimal.valueOf(product.getPrice()).setScale(2);
            this.inStock = product.isInStock();
            this.image = product.getImage();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
