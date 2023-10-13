package ru.ulstu.is.sbapp.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String productName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;
    public ProductEntity(){
    }
    public ProductEntity(String productName, OrderEntity order){
        this.productName = productName;
        this.order = order;
    }

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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity products = (ProductEntity) o;
        return Objects.equals(id, products.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", order='" + order.getId() + '\'' +
                '}';
    }
}
