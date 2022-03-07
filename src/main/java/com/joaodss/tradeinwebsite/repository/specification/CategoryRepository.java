package com.joaodss.tradeinwebsite.repository.specification;

import com.joaodss.tradeinwebsite.dao.specification.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByCategoryName(String categoryName);

    boolean existsByCategoryName(String categoryName);

}
