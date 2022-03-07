package com.joaodss.tradeinwebsite.repository.specification;

import com.joaodss.tradeinwebsite.dao.specification.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    List<Brand> findByBrandName(String brandName);

    boolean existsByBrandName(String brandName);

}
