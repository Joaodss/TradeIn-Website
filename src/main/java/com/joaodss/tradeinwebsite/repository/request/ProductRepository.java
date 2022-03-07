package com.joaodss.tradeinwebsite.repository.request;

import com.joaodss.tradeinwebsite.dao.request.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
