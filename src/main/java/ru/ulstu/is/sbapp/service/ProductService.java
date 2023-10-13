package ru.ulstu.is.sbapp.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ulstu.is.sbapp.model.OrderEntity;
import ru.ulstu.is.sbapp.model.ProductEntity;
import ru.ulstu.is.sbapp.repository.ProductRepository;
import ru.ulstu.is.sbapp.util.validation.ValidatorUtil;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final OrderService orderService;
    private final ValidatorUtil validatorUtil;
    public ProductService(ProductRepository productRepository, OrderService orderService, ValidatorUtil validatorUtil){
        this.productRepository = productRepository;
        this.orderService = orderService;
        this.validatorUtil = validatorUtil;
    }
    @Transactional
    public ProductEntity createProduct(Long orderId, String productName){
        OrderEntity order = orderService.findOrder(orderId);
        final ProductEntity product = new ProductEntity(productName, order);
        validatorUtil.validate(product);
        return productRepository.save(product);
    }
    @Transactional(readOnly = true)
    public ProductEntity findProduct(Long id) {
        final Optional<ProductEntity> product = productRepository.findById(id);
        return product.orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<ProductEntity> findAllProducts() {
        return productRepository.findAll();
    }
    @Transactional
    public ProductEntity updateProduct(Long id, Long orderId, String Name) {
        OrderEntity order = orderService.findOrder(orderId);
        final ProductEntity currentProductEntity = findProduct(id);
        final OrderEntity currentOrderEntity = currentProductEntity.getOrder();
        orderService.deleteProductInOrder(currentOrderEntity.getId(), currentProductEntity);
        currentProductEntity.setProductName(Name);
        currentProductEntity.setOrder(order);
        orderService.addProductInOrder(orderId, currentProductEntity);
        validatorUtil.validate(currentProductEntity);
        return productRepository.save(currentProductEntity);
    }
    @Transactional
    public ProductEntity deleteProduct(Long id) {
        final ProductEntity currentProductEntity = findProduct(id);
        final OrderEntity currentOrderEntity = currentProductEntity.getOrder();
        orderService.deleteProductInOrder(currentOrderEntity.getId(), currentProductEntity);
        productRepository.delete(currentProductEntity);
        return currentProductEntity;
    }
    @Transactional
    public void deleteAllProducts() {
        productRepository.deleteAll();
    }

}
