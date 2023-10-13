package ru.ulstu.is.sbapp.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ulstu.is.sbapp.model.Shop;
import ru.ulstu.is.sbapp.model.Shop;
import ru.ulstu.is.sbapp.repository.ShopRepository;
import ru.ulstu.is.sbapp.util.validation.ValidatorUtil;

import java.util.List;
import java.util.Optional;

@Service
public class ShopService {
    private final ShopRepository shopRepository;
    private final ValidatorUtil validatorUtil;
    public ShopService(ShopRepository shopRepository, ValidatorUtil validatorUtil){
        this.shopRepository = shopRepository;
        this.validatorUtil = validatorUtil;
    }
    @Transactional
    public Shop createShop(String Name){
        final Shop shop = new Shop(Name);
        validatorUtil.validate(shop);
        return shopRepository.save(shop);
    }
    @Transactional(readOnly = true)
    public Shop findShop(Long id) {
        final Optional<Shop> shop = shopRepository.findById(id);
        return shop.orElseThrow(() -> new ShopNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<Shop> findAllShops() {
        return shopRepository.findAll();
    }
    @Transactional
    public Shop updateShop(Long id, String Name) {
        final Shop currentShop = findShop(id);
        currentShop.setShopName(Name);
        validatorUtil.validate(currentShop);
        return shopRepository.save(currentShop);
    }
    @Transactional
    public Shop deleteShop(Long id) {
        final Shop currentShop = findShop(id);
        shopRepository.delete(currentShop);
        return currentShop;
    }
    @Transactional
    public void deleteAllShops() {
        shopRepository.deleteAll();
    }
}
