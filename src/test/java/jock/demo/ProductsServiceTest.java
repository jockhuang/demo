package jock.demo;

import jock.demo.dao.ProductsMapper;
import jock.demo.model.Products;
import jock.demo.service.BusinessException;
import jock.demo.service.ProductsService;
import jock.demo.service.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
        Assertions.assertNotEquals(products.getId().longValue(), 0L);

        //find the new list size
        List<Products> list1 = productsService.getComingSoonProduct();
        Integer newSize = list1.size();
        // it should be greater than origin size
        Assertions.assertNotEquals(originSize, newSize);

        //find by product name
        p2 = productsMapper.selectByPrimaryName("test1");
        //it should be not null
        Assertions.assertNotNull(p2);
        Assertions.assertEquals(p2.getName(),"test1");

        //remove the product
        productsService.removeComingSoonProducts(p2.getId());


        //the list size should be equals to the origin size
        list1 = productsService.getComingSoonProduct();
        newSize = list1.size();
        Assertions.assertEquals(originSize, newSize);
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
        if(p2!=null){
            productsService.removeCurrentProducts(p2.getId());

        }


        //record origin of list size
        List<Products> list = productsService.getAllCurrentProducts();
        Integer originSize = list.size();

        //insert a new product and if add success the products should have id.
        Products products = productsService.addCurrentProducts(p1);
        Assertions.assertNotEquals(products.getId().longValue(), 0L);


        //find the new list size
        List<Products> list1 = productsService.getAllCurrentProducts();
        Integer newSize = list1.size();

        // it should be greater than origin size
        Assertions.assertNotEquals(originSize, newSize);
        Assertions.assertNotNull(list1);

        //find by product name
        p2 = productsMapper.selectByPrimaryName("test2");
        //it should be not null
        Assertions.assertNotNull(p2);
        Assertions.assertEquals(p2.getName(),"test2");

        //remove the product
        productsService.removeCurrentProducts(p2.getId());

        //the list size should be equals to the origin size
        list1 = productsService.getAllCurrentProducts();
        newSize = list1.size();
        Assertions.assertEquals(originSize, newSize);

    }

    @Test
    public void currentAddNullTest() {
        Assertions.assertThrows(ValidationException.class,()->productsService.addCurrentProducts(null));
    }

    /**
     * the same data add twice will occur exception
     */
    @Test
    public void currentAddTwiceTest() {
        Products p1 = new Products();
        p1.setName("test2");
        p1.setDescription("just a test");
        productsService.addCurrentProducts(p1);

        Assertions.assertThrows(BusinessException.class,()->productsService.addCurrentProducts(p1));
        //clear
        Products p2 = productsMapper.selectByPrimaryName("test2");
        productsService.removeCurrentProducts(p2.getId());
    }

    /**
     * the same data remove twice will occur exception
     */
    @Test
    public void currentRemoveTwiceTest() {
        Products p1 = new Products();
        p1.setName("test2");
        p1.setDescription("just a test");
        p1 = productsService.addCurrentProducts(p1);

        productsService.removeCurrentProducts(p1.getId());
        Products finalP = p1;
        Assertions.assertThrows(BusinessException.class,()->productsService.removeCurrentProducts(finalP.getId()));

    }

    @Test
    public void currentAddEmptyTest() {
        Products products = new Products();
        Assertions.assertThrows(ValidationException.class,()->productsService.addCurrentProducts(products));
    }

    @Test
    public void soonAddNullTest() {
        Assertions.assertThrows(ValidationException.class,()->productsService.addComingSoonProducts(null));
    }

    @Test
    public void soonAddEmptyTest() {
        Products products = new Products();
        Assertions.assertThrows(ValidationException.class,()->productsService.addComingSoonProducts(products));
    }

    /**
     * the same data add twice will occur exception
     */
    @Test
    public void soonAddTwiceTest() {
        Products p1 = new Products();
        p1.setName("test1");
        p1.setDescription("just a test");
        productsService.addComingSoonProducts(p1);
        Assertions.assertThrows(BusinessException.class,()->productsService.addComingSoonProducts(p1));
        //clear
        Products p2 = productsMapper.selectByPrimaryName("test1");
        productsService.removeCurrentProducts(p2.getId());

    }

    /**
     * the same data remove twice will occur exception
     */
    @Test
    public void soonRemoveTwiceTest() {
        Products p1 = new Products();
        p1.setName("test1");
        p1.setDescription("just a test");
        p1 = productsService.addComingSoonProducts(p1);

        productsService.removeComingSoonProducts(p1.getId());

        Products finalP = p1;
        Assertions.assertThrows(BusinessException.class,()->productsService.removeComingSoonProducts(finalP.getId()));
    }

    @Test
    public void currentRemoveNullTest() {
        Assertions.assertThrows(ValidationException.class,()->productsService.removeCurrentProducts(null));
    }

    @Test
    public void currentRemoveEmptyTest() {
        Assertions.assertThrows(BusinessException.class,()->productsService.removeCurrentProducts(Integer.MAX_VALUE));
    }

    @Test
    public void soonRemoveNullTest() {
        Assertions.assertThrows(ValidationException.class,()->productsService.removeComingSoonProducts(null));
    }

    @Test
    public void soonRemoveEmptyTest() {
        Assertions.assertThrows(BusinessException.class,()->productsService.removeComingSoonProducts(Integer.MAX_VALUE));
    }
}
