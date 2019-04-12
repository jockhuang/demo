package jock.demo.service;

import jock.demo.model.Products;

import java.util.List;

public interface ProductsService {
    List<Products> getAllCurrentProducts();

    List<Products> getComingSoonProduct();
}
