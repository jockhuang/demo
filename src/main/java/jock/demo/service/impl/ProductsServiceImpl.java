package jock.demo.service.impl;

import jock.demo.dao.ProductsMapper;
import jock.demo.model.Products;
import jock.demo.service.BusinessException;
import jock.demo.service.ProductsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ProductsServiceImpl implements ProductsService {

    @Resource
    private ProductsMapper productsMapper;

    @Override
    public List<Products> getAllCurrentProducts() {
        return productsMapper.selectReleased(true);

    }

    @Override
    public List<Products> getComingSoonProduct() {
        return productsMapper.selectReleased(false);
    }

    /**
     * check the input parameter of add method.
     *
     * @param products
     */
    private void checkAddProducts(Products products) {
        if (products == null) {
            throw new BusinessException("Product is empty!");
        }
        if (products.getName() == null || "".equals(products.getName().trim())) {
            throw new BusinessException("Product name is empty!");
        }
    }


    @Override
    @Transactional
    public Products addCurrentProducts(Products products) {
        checkAddProducts(products);
        products.setIsRelease(true);
        return saveToDb(products);
    }

    @Override
    @Transactional
    public Products addComingSoonProducts(Products products) {
        checkAddProducts(products);
        products.setIsRelease(false);
        return saveToDb(products);
    }

    /**
     * set the default value of fields and insert into table.
     *
     * @param products
     * @return
     */
    private Products saveToDb(Products products) {
        Date now = new Date();
        products.setCreateDate(now);
        products.setUpdateDate(now);
        productsMapper.insert(products);
        return products;
    }

    @Override
    @Transactional
    public void removeCurrentProducts(Integer productId) {
        if (productId == null|| productId<=0) {
            throw new BusinessException("Product id cannot be empty and should be greater than 0!");
        }
        Products products = productsMapper.selectByPrimaryKey(productId);
        if (products == null || !products.getIsRelease()) {
            throw new BusinessException("Product does not exist or is not released!");
        }
        productsMapper.deleteByPrimaryKey(productId);


    }

    @Override
    @Transactional
    public void removeComingSoonProducts(Integer productId) {
        if (productId == null|| productId<=0) {
            throw new BusinessException("Product id cannot be empty and should be greater than 0!");
        }
        Products products = productsMapper.selectByPrimaryKey(productId);
        if (products == null || products.getIsRelease()) {
            throw new BusinessException("Product does not exist or is released!");
        }
        productsMapper.deleteByPrimaryKey(productId);
    }
}
