package ru.ulstu.is.sbapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ulstu.is.sbapp.model.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
