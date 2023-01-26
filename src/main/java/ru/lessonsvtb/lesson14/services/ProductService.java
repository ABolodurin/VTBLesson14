package ru.lessonsvtb.lesson14.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lessonsvtb.lesson14.entities.Product;
import ru.lessonsvtb.lesson14.repositories.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getById(Long id) {
        return productRepository.findById(id);
    }

    public void removeProduct(Long id) {
        productRepository.remove(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getFilteredProducts(String filter, Integer minPrice, Integer maxPrice) {
        int min = minPrice == null ? Integer.MIN_VALUE : minPrice;
        int max = maxPrice == null ? Integer.MAX_VALUE : maxPrice;
        return productRepository.findAll().stream()
                .filter(p -> p.getTitle().contains(filter) &&
                        p.getPrice() >= min &&
                        p.getPrice() <= max)
                .collect(Collectors.toList());
    }

    public void updateProduct(Long id, Product updatedProduct) {
        productRepository.updateProduct(id, updatedProduct);
    }

    public void add(Product product) {
        productRepository.save(product);
    }
}
