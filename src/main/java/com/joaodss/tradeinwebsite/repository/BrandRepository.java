package com.joaodss.tradeinwebsite.repository;

import com.joaodss.tradeinwebsite.dao.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
}
