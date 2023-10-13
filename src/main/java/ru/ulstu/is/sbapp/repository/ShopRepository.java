package ru.ulstu.is.sbapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ulstu.is.sbapp.model.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long> {
}
