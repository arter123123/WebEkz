package ru.ulstu.is.sbapp.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ProductEntity> productList = new ArrayList<>();
    @Column
    private Integer price;
    public OrderEntity(){
    }
    public OrderEntity(Integer price){
        this.price = price;
    }
    public Long getId() {
        return id;
    }

    public Integer getPrice() {
        return price;
    }
    public List<String> getproductNameList(){
        List<String> productNameList = null;
        for (int i = 0; i < productList.size(); i++){
            productNameList.add(productList.get(i).getProductName());
        }
        return productNameList;
    }

    public List<ProductEntity> getProductList() {
        return productList;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
    public void addProduct(ProductEntity product){
        productList.add(product);
        product.setOrder(this);
    }
    public void deleteProduct(ProductEntity product){
        productList.remove(product);
        product.setOrder(null);
    }
    public void setProductList(List<ProductEntity> productList) {
        this.productList = productList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity orders = (OrderEntity) o;
        return Objects.equals(id, orders.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", productList='" + productList + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

}
