package ru.lessonsvtb.lesson14.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lessonsvtb.lesson14.entities.ProductDetails;
import ru.lessonsvtb.lesson14.repositories.ProductDetailsRepository;
import java.util.List;

@Service
public class ProductDetailsService {
    private ProductDetailsRepository productDetailsRepository;

    @Autowired
    public void setProductDetailsRepository(ProductDetailsRepository productDetailsRepository) {
        this.productDetailsRepository = productDetailsRepository;
    }

    public void logView(Long id){
        productDetailsRepository.incrementView(id);
    }

    @Transactional
    public void add(ProductDetails productDetails){
        productDetailsRepository.save(productDetails);
    }

    public ProductDetails getById(Long id){
        return productDetailsRepository.findById(id).get();
    }

    public List<ProductDetails> getMostViewed(int limit){
        return productDetailsRepository.findMostViewed( limit);
    }

}
