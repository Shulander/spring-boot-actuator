package us.vicentini.controllers;

import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import us.vicentini.services.ProductService;


@Controller
public class IndexController {

    private ProductService productService;


    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }


    @Timed("us.vicentini.controllers.IndexController.getIndex.timed")
    @GetMapping({"/", "index"})
    public String getIndex(Model model) {

        model.addAttribute("products", productService.listProducts());

        return "index";
    }


    @Timed("us.vicentini.controllers.IndexController.secured.timed")
    @GetMapping("secured")
    public String secured() {
        return "secured";
    }
}
