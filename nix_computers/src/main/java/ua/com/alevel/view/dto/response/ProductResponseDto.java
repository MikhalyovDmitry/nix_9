package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Order;
import ua.com.alevel.view.dto.ResponseDto;
import ua.com.alevel.persistence.entity.Product;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class ProductResponseDto extends ResponseDto {

    private String name;
    private BigDecimal price;
    private boolean inStock;
    private String image;
    private List<Order> orders;
    private int sales;


    public ProductResponseDto() {
    }

    public ProductResponseDto(Product product) {
        if (product != null) {
            super.setId(product.getId());
            this.name = product.getName();
            this.price = BigDecimal.valueOf(product.getPrice()).setScale(2);
            this.inStock = product.isInStock();
            this.image = product.getImage();
            this.orders = product.getOrders();
            this.sales = product.getSales();
        }
    }

    public int getSales() {
        int sales = 0;
        List<Order> orders = this.getOrders();
        sales = (int) orders.
                stream().
                map(Order::getProducts).
                flatMap(Collection::stream).
                filter(product -> Objects.equals(product.getId(), this.getId())).
                count();
        return sales;
    }


    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
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
