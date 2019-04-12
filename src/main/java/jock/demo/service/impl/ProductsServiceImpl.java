package jock.demo.service.impl;

import jock.demo.dao.ProductsMapper;
import jock.demo.model.Products;
import jock.demo.service.ProductsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
}
