package com.joaodss.tradeinwebsite.categorybrandspecification.repository;

import com.joaodss.tradeinwebsite.categorybrandspecification.dao.CategoryBrandSpecification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryBrandSpecificationRepository extends JpaRepository<CategoryBrandSpecification, Long> {

    Optional<CategoryBrandSpecification> findByCategory_CategoryNameAndBrand_BrandName(String categoryName, String brandName);

    boolean existsByCategory_CategoryNameAndBrand_BrandName(String categoryName, String brandName);

    List<CategoryBrandSpecification> findByCategory_CategoryName(String categoryName);

    List<CategoryBrandSpecification> findByBrand_BrandName(String brandName);

}
