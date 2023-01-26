package ru.lessonsvtb.lesson14.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.lessonsvtb.lesson14.entities.Product;
import ru.lessonsvtb.lesson14.services.ProductService;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private ProductService productsService;

    @Autowired
    public void setProductsService(ProductService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public String showProductsList(Model model, @RequestParam(value = "filter", required = false) String filter) {
        Product product = new Product();
        if (filter == null) {
            model.addAttribute("products", productsService.getAllProducts());
        }else
            model.addAttribute("products", productsService.getFilteredProducts(filter));
        model.addAttribute("filter", filter);
        model.addAttribute("product", product);
        return "products";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute(value = "product")Product product) {
        productsService.add(product);
        return "redirect:/products";
    }

    @GetMapping("/show/{id}")
    public String showOneProduct(Model model, @PathVariable(value = "id") Long id) {
        Product product = productsService.getById(id);
        model.addAttribute("product", product);
        return "product-page";
    }


    @PostMapping("show/{id}/remove")
    public String removeProduct(@PathVariable(value = "id") Long id){
        productsService.removeProduct(id);
        return "redirect:/products";
    }
}
