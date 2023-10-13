package ru.ulstu.is.sbapp.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.ulstu.is.sbapp.model.OrderEntity;
import ru.ulstu.is.sbapp.model.ProductEntity;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class ProductDto {
    private long id;
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private OrderEntity order;
    private String productName;
    public ProductDto() {
    }
    public ProductDto(ProductEntity product) {
        this.id = product.getId();
        this.order = product.getOrder();
        this.productName = product.getProductName();
    }
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public Long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }
}

