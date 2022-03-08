package com.joaodss.tradeinwebsite.categorybrandspecification.repository;

import com.joaodss.tradeinwebsite.categorybrandspecification.dao.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByCategoryName(String categoryName);

    boolean existsByCategoryName(String categoryName);

}
