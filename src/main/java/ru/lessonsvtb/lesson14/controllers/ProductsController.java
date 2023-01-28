package ru.lessonsvtb.lesson14.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.lessonsvtb.lesson14.entities.Product;
import ru.lessonsvtb.lesson14.services.ProductService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private ProductService productsService;

    @Autowired
    public void setProductsService(ProductService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public String showProductsList(Model model, @RequestParam(value = "filter", required = false) String filter,
                                   @RequestParam(value = "from", required = false) Integer minPrice,
                                   @RequestParam(value = "to", required = false) Integer maxPrice,
                                   @RequestParam(value = "page", required = false) Integer page) {
        Product product = new Product();
        Specification<Product> filters = Specification.where(null);
        if (page == null) page = 0;
        if (filter == null) {
            Page<Product> products = productsService.productPage(filters, PageRequest.of(page, 10));
            model.addAttribute("products", products.getContent());
            int totalPages = products.getTotalPages();
            List<Integer> pageNumbers = new ArrayList<>();
            for (int i = 0; i < totalPages; i++) {
                pageNumbers.add(i);
            }
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("pageNumbers", pageNumbers);
        } else
            model.addAttribute("products", productsService.getFilteredProducts(filter, minPrice, maxPrice));
        model.addAttribute("filter", filter);
        model.addAttribute("product", product);
        model.addAttribute("from", minPrice);
        model.addAttribute("to", maxPrice);
        model.addAttribute("page", page);
        return "products";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute(value = "product") Product product) {
        productsService.add(product);
        return "redirect:/products";
    }

    @GetMapping("/show/{id}")
    public String showOneProduct(Model model, @PathVariable(value = "id") Long id) {
        Product product = productsService.getById(id);
        Product editProduct = new Product();
        model.addAttribute("product", product);
        model.addAttribute("editProduct", editProduct);
        return "product-page";
    }


    @PostMapping("show/{id}/remove")
    public String removeProduct(@PathVariable(value = "id") Long id) {
        productsService.removeProduct(id);
        return "redirect:/products";
    }

    @PostMapping("show/{id}/update")
    public String updateProduct(@PathVariable(value = "id") Long id, Product updatedProduct) {
        productsService.updateProduct(id, updatedProduct);
        return "redirect:/products";
    }

}
