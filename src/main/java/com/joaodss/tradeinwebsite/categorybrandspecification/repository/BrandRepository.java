package com.joaodss.tradeinwebsite.categorybrandspecification.repository;

import com.joaodss.tradeinwebsite.categorybrandspecification.dao.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    List<Brand> findByBrandName(String brandName);

    boolean existsByBrandName(String brandName);

}
