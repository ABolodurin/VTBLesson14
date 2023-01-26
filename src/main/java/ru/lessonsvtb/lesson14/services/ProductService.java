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

    public void removeProduct(Long id){
        productRepository.remove(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getFilteredProducts(String filter) {
        return productRepository.findAll().stream()
                .filter(p-> p.getTitle().contains(filter))
                .collect(Collectors.toList());
    }

    public void add(Product product) {
        productRepository.save(product);
    }
}
