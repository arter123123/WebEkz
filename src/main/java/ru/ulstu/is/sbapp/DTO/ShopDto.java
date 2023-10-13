package ru.ulstu.is.sbapp.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.ulstu.is.sbapp.model.Shop;

import javax.validation.constraints.NotBlank;

public class ShopDto {
    private long id;
    @NotBlank
    private String shopName;
    public ShopDto(){
    }
    public ShopDto(Shop shop){
        this.id = shop.getId();
        this.shopName = shop.getShopName();
    }
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public long getId() {
        return id;
    }
    public String getShopName() {
        return shopName;
    }
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
