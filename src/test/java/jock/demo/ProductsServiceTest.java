package jock.demo;

import jock.demo.dao.ProductsMapper;
import jock.demo.model.Products;
import jock.demo.service.BusinessException;
import jock.demo.service.ProductsService;
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
    public void comingSoonProductTest(){
        Products p1 = new Products();
        p1.setName("test1");
        p1.setDescription("just a test");
        p1.setCreateDate(new Date());
        p1.setUpdateDate(new Date());
        p1.setIsRelease(false);
        p1.setIsDelete(false);

        List<Products> list = productsService.getComingSoonProduct();
        Integer originSize = list.size();

        Products products = productsService.addComingSoonProducts(p1);
        Assert.assertNotEquals(products.getId().longValue(),0L);


        List<Products> list1 = productsService.getComingSoonProduct();

        Integer newSize = list1.size();
        Assert.assertNotEquals(originSize,newSize);

        Products p2 = productsMapper.selectByPrimaryName("test1");

        Assert.assertNotNull(p2);
        System.out.println(p2.getId());

        Assert.assertEquals(productsMapper.deleteByPrimaryKey(p2.getId()),1);

        list1 = productsService.getComingSoonProduct();
        newSize = list1.size();
        Assert.assertEquals(originSize,newSize);
    }

    /**
     * include crud normal process of have been released products
     */
    @Test
    public void releasedProductTest(){
        Products p1 = new Products();
        p1.setName("test2");
        p1.setDescription("just a test");
        p1.setCreateDate(new Date());
        p1.setUpdateDate(new Date());
        p1.setIsRelease(true);
        p1.setIsDelete(false);

        List<Products> list = productsService.getAllCurrentProducts();
        Integer originSize = list.size();

        Products products = productsService.addCurrentProducts(p1);
        Assert.assertNotEquals(products.getId().longValue(),0L);

        List<Products> list1 = productsService.getAllCurrentProducts();
        Integer newSize = list1.size();
        Assert.assertNotEquals(originSize,newSize);
        Assert.assertNotNull(list1);

        Products p2 = productsMapper.selectByPrimaryName("test2");

        Assert.assertNotNull(p2);

        Assert.assertEquals(productsMapper.deleteByPrimaryKey(p2.getId()),1);

        list1 = productsService.getAllCurrentProducts();
        newSize = list1.size();
        Assert.assertEquals(originSize,newSize);

    }

    @Test(expected = BusinessException.class)
    public void currentAddNullTest(){
        productsService.addCurrentProducts(null);
    }

    @Test(expected = BusinessException.class)
    public void currentAddEmptyTest(){
        Products products = new Products();
        productsService.addCurrentProducts(products);
    }

    @Test(expected = BusinessException.class)
    public void soonAddNullTest(){
        productsService.addComingSoonProducts(null);
    }

    @Test(expected = BusinessException.class)
    public void soonAddEmptyTest(){
        Products products = new Products();
        productsService.addComingSoonProducts(products);
    }

    @Test(expected = BusinessException.class)
    public void currentRemoveNullTest(){
        productsService.removeCurrentProducts(null);
    }

    @Test(expected = BusinessException.class)
    public void currentRemoveEmptyTest(){
        productsService.removeCurrentProducts(Integer.MAX_VALUE);
    }

    @Test(expected = BusinessException.class)
    public void soonRemoveNullTest(){
        productsService.removeComingSoonProducts(null);
    }

    @Test(expected = BusinessException.class)
    public void soonRemoveEmptyTest(){
        productsService.removeComingSoonProducts(Integer.MAX_VALUE);
    }
}
