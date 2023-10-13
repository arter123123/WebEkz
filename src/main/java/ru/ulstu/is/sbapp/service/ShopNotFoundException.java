package ru.ulstu.is.sbapp.service;

public class ShopNotFoundException extends RuntimeException{
    public ShopNotFoundException(Long id) {
        super(String.format("Shop with id [%s] is not found", id));
    }
}