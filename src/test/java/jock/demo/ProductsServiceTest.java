package jock.demo;

import jock.demo.dao.ProductsMapper;
import jock.demo.model.Products;
import jock.demo.service.BusinessException;
import jock.demo.service.ProductsService;
import jock.demo.service.ValidationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductsServiceTest {
    @Resource
    ProductsService productsService;

    @Resource
    private ProductsMapper productsMapper;

    /**
     * include crud normal process of soon to be released product
     */
    @Test
    public void comingSoonProductTest() {
        Products p1 = new Products();
        p1.setName("test1");
        p1.setDescription("just a test");

        //clear
        Products p2 = productsMapper.selectByPrimaryName("test1");
        productsService.removeComingSoonProducts(p2.getId());

        //record origin of list size
        List<Products> list = productsService.getComingSoonProduct();
        Integer originSize = list.size();

        //insert a new product and if add success the products should have id.
        Products products = productsService.addComingSoonProducts(p1);
        Assert.assertNotEquals(products.getId().longValue(), 0L);

        //find the new list size
        List<Products> list1 = productsService.getComingSoonProduct();
        Integer newSize = list1.size();
        // it should be greater than origin size
        Assert.assertNotEquals(originSize, newSize);

        //find by product name
        p2 = productsMapper.selectByPrimaryName("test1");
        //it should be not null
        Assert.assertNotNull(p2);
        Assert.assertEquals(p2.getName(),"test1");

        //remove the product
        productsService.removeComingSoonProducts(p2.getId());


        //the list size should be equals to the origin size
        list1 = productsService.getComingSoonProduct();
        newSize = list1.size();
        Assert.assertEquals(originSize, newSize);
    }

    /**
     * include crud normal process of have been released products
     */
    @Test
    public void releasedProductTest() {
        Products p1 = new Products();
        p1.setName("test2");
        p1.setDescription("just a test");
        //clear
        Products p2 = productsMapper.selectByPrimaryName("test2");
        productsService.removeCurrentProducts(p2.getId());


        //record origin of list size
        List<Products> list = productsService.getAllCurrentProducts();
        Integer originSize = list.size();

        //insert a new product and if add success the products should have id.
        Products products = productsService.addCurrentProducts(p1);
        Assert.assertNotEquals(products.getId().longValue(), 0L);


        //find the new list size
        List<Products> list1 = productsService.getAllCurrentProducts();
        Integer newSize = list1.size();

        // it should be greater than origin size
        Assert.assertNotEquals(originSize, newSize);
        Assert.assertNotNull(list1);

        //find by product name
        p2 = productsMapper.selectByPrimaryName("test2");
        //it should be not null
        Assert.assertNotNull(p2);
        Assert.assertEquals(p2.getName(),"test2");

        //remove the product
        productsService.removeCurrentProducts(p2.getId());

        //the list size should be equals to the origin size
        list1 = productsService.getAllCurrentProducts();
        newSize = list1.size();
        Assert.assertEquals(originSize, newSize);

    }

    @Test(expected = ValidationException.class)
    public void currentAddNullTest() {
        productsService.addCurrentProducts(null);
    }

    /**
     * the same data add twice will occur exception
     */
    @Test(expected = BusinessException.class)
    public void currentAddTwiceTest() {
        Products p1 = new Products();
        p1.setName("test2");
        p1.setDescription("just a test");
        productsService.addCurrentProducts(p1);
        productsService.addCurrentProducts(p1);

    }

    @Test(expected = ValidationException.class)
    public void currentAddEmptyTest() {
        Products products = new Products();
        productsService.addCurrentProducts(products);
    }

    @Test(expected = ValidationException.class)
    public void soonAddNullTest() {
        productsService.addComingSoonProducts(null);
    }

    @Test(expected = ValidationException.class)
    public void soonAddEmptyTest() {
        Products products = new Products();
        productsService.addComingSoonProducts(products);
    }

    /**
     * the same data add twice will occur exception
     */
    @Test(expected = BusinessException.class)
    public void soonAddTwiceTest() {
        Products p1 = new Products();
        p1.setName("test1");
        p1.setDescription("just a test");
        productsService.addComingSoonProducts(p1);
        productsService.addComingSoonProducts(p1);

    }

    @Test(expected = ValidationException.class)
    public void currentRemoveNullTest() {
        productsService.removeCurrentProducts(null);
    }

    @Test(expected = BusinessException.class)
    public void currentRemoveEmptyTest() {
        productsService.removeCurrentProducts(Integer.MAX_VALUE);
    }

    @Test(expected = ValidationException.class)
    public void soonRemoveNullTest() {
        productsService.removeComingSoonProducts(null);
    }

    @Test(expected = BusinessException.class)
    public void soonRemoveEmptyTest() {
        productsService.removeComingSoonProducts(Integer.MAX_VALUE);
    }
}
