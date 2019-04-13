package jock.demo.service;

import jock.demo.model.Products;

import java.util.List;

public interface ProductsService {
    List<Products> getAllCurrentProducts();

    List<Products> getComingSoonProduct();

    Products addCurrentProducts(Products products);

    Products addComingSoonProducts(Products products);

    void removeCurrentProducts(Integer productId);

    void removeComingSoonProducts(Integer productId);
}
