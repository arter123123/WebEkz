package ru.ulstu.is.sbapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ulstu.is.sbapp.model.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
