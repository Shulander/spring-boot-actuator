package us.vicentini.controllers;

import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import us.vicentini.services.ProductService;


@Controller
@Timed
public class ProductController {

    private ProductService productService;


    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }


    @Timed("us.vicentini.controllers.ProductController.getProductById.timed")
    @GetMapping("/product/{id}")
    public String getProductById(@PathVariable Integer id, Model model) {

        model.addAttribute("product", productService.getProduct(id));

        return "product";
    }
}
