package jock.demo;

import jock.demo.dao.ProductsMapper;
import jock.demo.model.Products;
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

    @Test
    public void comingSoonProductTest(){
        Products p1 = new Products();
        p1.setName("test1");
        p1.setDescription("just a test");
        p1.setCreateDate(new Date());
        p1.setUpdateDate(new Date());
        p1.setIsRelease(false);
        p1.setIsDelete(false);
        Assert.assertEquals(productsMapper.insert(p1),1);


        List<Products> list1 = productsService.getComingSoonProduct();
        System.out.println(list1.size());
        Assert.assertNotNull(list1);

        Products p2 = productsMapper.selectByPrimaryName("test1");

        Assert.assertNotNull(p2);
        System.out.println(p2.getId());

        Assert.assertEquals(productsMapper.deleteByPrimaryKey(p2.getId()),1);

    }


    @Test
    public void releasedProductTest(){
        Products p1 = new Products();
        p1.setName("test2");
        p1.setDescription("just a test");
        p1.setCreateDate(new Date());
        p1.setUpdateDate(new Date());
        p1.setIsRelease(true);
        p1.setIsDelete(false);
        Assert.assertEquals(productsMapper.insert(p1),1);

        List<Products> list1 = productsService.getAllCurrentProducts();
        System.out.println(list1.size());
        Assert.assertNotNull(list1);

        Products p2 = productsMapper.selectByPrimaryName("test2");

        Assert.assertNotNull(p2);
        System.out.println(p2.getId());

        Assert.assertEquals(productsMapper.deleteByPrimaryKey(p2.getId()),1);


    }
}
