package jock.demo.controller;

import jock.demo.model.Products;
import jock.demo.service.ProductsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

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
     * @return the json format data of the coming soon product list
     */
    @RequestMapping(value = "/getComingSoon", method = GET)
    public MyReponseBody getComingSoonProduct() {
        List<Products> list = productsService.getComingSoonProduct();
        return MyReponseBody.ok(list);
    }

}
