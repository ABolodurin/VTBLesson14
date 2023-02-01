package ru.lessonsvtb.lesson14.entities;

import javax.persistence.*;

@Entity
@Table(name = "product_details")
public class ProductDetails {
    @Id
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "views")
    private Long views;

    @OneToOne
    @MapsId
    private Product product;

    public ProductDetails(Long productId, Long views, Product product) {
        this.productId = productId;
        this.views = views;
        this.product = product;
    }

    public ProductDetails() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
