package ru.lessonsvtb.lesson14.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.lessonsvtb.lesson14.entities.Product;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>, JpaSpecificationExecutor<Product>,
        JpaRepository<Product, Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE Product p SET p.title = :title, p.price = :price WHERE p.id =:id")
    void updateById(@Param("id") Long id, @Param("title") String title, @Param("price") int price);
}
