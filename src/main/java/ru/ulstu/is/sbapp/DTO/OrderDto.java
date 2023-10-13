package ru.ulstu.is.sbapp.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.ulstu.is.sbapp.model.OrderEntity;
import ru.ulstu.is.sbapp.model.ProductEntity;

import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class OrderDto {
    private long id;
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private List<ProductEntity> productList;
    private List<String> productNameList;
    private Integer price;
    public OrderDto() {
    }
    public OrderDto(OrderEntity order) {
        this.id = order.getId();
        this.productList = order.getProductList();
        this.price = order.getPrice();
    }
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public Long getId() {
        return id;
    }
    public List<ProductEntity> getProductList() {
        return this.productList;
    }

    public Integer getPrice() {
        return price;
    }

    public List<String> getProductNameList() {
        return productNameList;
    }

    public void setProductList(List<ProductEntity> productList) {
        this.productList = productList;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setProductNameList(List<String> productNameList) {
        this.productNameList = productNameList;
    }
}
