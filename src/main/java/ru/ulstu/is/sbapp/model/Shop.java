package ru.ulstu.is.sbapp.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String shopName;
    public Shop(){
    }
    public Shop(String shopName){
        this.shopName = shopName;
    }

    public Long getId() {
        return id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shop shops = (Shop) o;
        return Objects.equals(id, shops.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", shopName='" + this.shopName + '\'' +
                '}';
    }
}
