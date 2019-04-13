package jock.demo.controller;

import jock.demo.model.Products;
import jock.demo.service.BusinessException;
import jock.demo.service.ProductsService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductsService productsService;


    /**
     * Get all current products
     * <pre>
     * ENDPOINT: http://localhost:8080/product/getAllCurrent
     * RequestMethod: GET
     * </pre>
     *
     * @return the json format data of current product list
     */
    @RequestMapping(value = "/getAllCurrent", method = GET)
    public MyReponseBody getAllCurrentProduct() {
        List<Products> list = productsService.getAllCurrentProducts();
        return MyReponseBody.ok(list);
    }

    /**
     * Get soon to be released products
     * <pre>
     * ENDPOINT: http://localhost:8080/product/getComingSoon
     * RequestMethod: GET
     * </pre>
     *
     * @return the json format data of the coming soon product list
     */
    @RequestMapping(value = "/getComingSoon", method = GET)
    public MyReponseBody getComingSoonProduct() {
        List<Products> list = productsService.getComingSoonProduct();
        return MyReponseBody.ok(list);
    }


    /**
     * add a released product
     * <pre>
     * ENDPOINT: http://localhost:8080/product/current
     * RequestMethod: POST
     * </pre>
     * @return the json format data of the released product that just added
     */
    @RequestMapping(value = "/current", method = POST)
    public MyReponseBody addCurrentProduct(@RequestBody Products products) {
        if (products.getName() == null || "".equals(products.getName())) {
            throw new BusinessException("Product name is empty!");
        }
        Products products1 = productsService.addCurrentProducts(products);

        return MyReponseBody.ok(products1);
    }

    /**
     * add a soon to be released product
     * <pre>
     * ENDPOINT: http://localhost:8080/product/comingSoon
     * RequestMethod: POST
     * </pre>
     * @return the json format data of the coming soon product just added
     */
    @RequestMapping(value = "/comingSoon", method = POST)
    public MyReponseBody addComingSoonProduct(@RequestBody Products products) {
        if (products.getName() == null || "".equals(products.getName())) {
            throw new BusinessException("Product name is empty!");
        }
        Products products1 = productsService.addComingSoonProducts(products);
        return MyReponseBody.ok(products1);
    }

    /**
     * delete a released product
     * <pre>
     * ENDPOINT: http://localhost:8080/product/current/{productId}
     * RequestMethod: DELETE
     * </pre>
     * @return the result of delete
     */
    @RequestMapping(value = "/current/{productId}", method = DELETE)
    public MyReponseBody removeCurrentProduct(@PathVariable Integer productId) {
        productsService.removeCurrentProducts(productId);
        return MyReponseBody.ok();
    }


    /**
     * delete a soon to be released product
     * <pre>
     * ENDPOINT: http://localhost:8080/product/comingSoon/{productId}
     * RequestMethod: DELETE
     * </pre>
     * @return the result of delete
     */
    @RequestMapping(value = "/comingSoon/{productId}", method = DELETE)
    public MyReponseBody removeComingSoonProduct(@PathVariable Integer productId) {
        productsService.removeComingSoonProducts(productId);
        return MyReponseBody.ok();
    }

}
