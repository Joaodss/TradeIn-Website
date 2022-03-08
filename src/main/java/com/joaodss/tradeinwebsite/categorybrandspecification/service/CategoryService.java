package com.joaodss.tradeinwebsite.categorybrandspecification.service;

import com.joaodss.tradeinwebsite.categorybrandspecification.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    // -------------------- Get Categories --------------------
    List<CategoryDTO> getAllCategories();

    List<String> getAllCategoryNames();

    CategoryDTO getCategoryById(long Id);

    List<CategoryDTO> getCategoryByName(String categoryName);

    // -------------------- Create Category --------------------
    List<String> createCategories(List<String> categories);

    // -------------------- Delete Category --------------------
    CategoryDTO deleteCategoryById(long id);

}
