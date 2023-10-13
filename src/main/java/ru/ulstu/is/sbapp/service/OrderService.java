package ru.ulstu.is.sbapp.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ulstu.is.sbapp.model.OrderEntity;
import ru.ulstu.is.sbapp.model.ProductEntity;
import ru.ulstu.is.sbapp.repository.OrderRepository;
import ru.ulstu.is.sbapp.util.validation.ValidatorUtil;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ValidatorUtil validatorUtil;
    public OrderService(OrderRepository orderRepository, ValidatorUtil validatorUtil){
        this.orderRepository = orderRepository;
        this.validatorUtil = validatorUtil;
    }
    @Transactional
    public OrderEntity createOrder(Integer price){
        final OrderEntity order = new OrderEntity(price);
        validatorUtil.validate(order);
        return orderRepository.save(order);
    }
    @Transactional(readOnly = true)
    public OrderEntity findOrder(Long id) {
        final Optional<OrderEntity> order = orderRepository.findById(id);
        return order.orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<OrderEntity> findAllOrders() {
        return orderRepository.findAll();
    }
    @Transactional
    public OrderEntity updateOrder(Long id, Integer price) {
        final OrderEntity currentOrderEntity = findOrder(id);
        currentOrderEntity.setPrice(price);
        validatorUtil.validate(currentOrderEntity);
        return orderRepository.save(currentOrderEntity);
    }
    @Transactional
    public OrderEntity addProductInOrder(Long id, ProductEntity product){
        final OrderEntity currentOrderEntity = findOrder(id);
        currentOrderEntity.addProduct(product);
        validatorUtil.validate(currentOrderEntity);
        return orderRepository.save(currentOrderEntity);
    }
    @Transactional
    public OrderEntity deleteOrder(Long id) {
        final OrderEntity currentOrderEntity = findOrder(id);
        orderRepository.delete(currentOrderEntity);
        return currentOrderEntity;
    }
    @Transactional
    public OrderEntity deleteProductInOrder(Long id, ProductEntity product){
        final OrderEntity currentOrderEntity = findOrder(id);
        currentOrderEntity.deleteProduct(product);
        validatorUtil.validate(currentOrderEntity);
        return orderRepository.save(currentOrderEntity);
    }
    @Transactional
    public void deleteAllOrders() {
        orderRepository.deleteAll();
    }
}
