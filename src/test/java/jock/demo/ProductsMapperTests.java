package jock.demo;

import jock.demo.dao.ProductsMapper;
import jock.demo.model.Products;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductsMapperTests {

    @Resource
    private ProductsMapper productsMapper;

    @Test
    public void crudTest() {
        Products p1 = new Products();
        p1.setName("test1");
        p1.setDescription("just a test");
        p1.setCreateDate(new Date());
        p1.setUpdateDate(new Date());
        p1.setIsRelease(false);
        p1.setIsDelete(false);

        Assert.assertEquals(productsMapper.insert(p1), 1);

        Products p2 = productsMapper.selectByPrimaryName("test1");

        Assert.assertNotNull(p2);
        System.out.println(p2.getId());

        Assert.assertEquals(productsMapper.deleteByPrimaryKey(p2.getId()), 1);

    }

}
