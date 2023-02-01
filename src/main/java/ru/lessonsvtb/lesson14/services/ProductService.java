package ru.lessonsvtb.lesson14.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.lessonsvtb.lesson14.entities.Product;
import ru.lessonsvtb.lesson14.repositories.ProductRepository;
import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;


    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getById(Long id) {
        return productRepository.findById(id).get();
    }

    public void removeProduct(Long id) {
        productRepository.delete(getById(id));
    }

    public Page<Product> productPage(Specification<Product> specifications, Pageable pageable) {
        return productRepository.findAll(specifications, pageable);
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public void updateProduct(Long id, Product updatedProduct) {
        productRepository.updateById(id, updatedProduct.getTitle(), updatedProduct.getPrice());
    }

    public void add(Product product) {
        productRepository.save(product);
    }

}
