package com.joaodss.tradeinwebsite.categorybrandspecification.service;

import com.joaodss.tradeinwebsite.categorybrandspecification.dao.Category;
import com.joaodss.tradeinwebsite.categorybrandspecification.dto.CategoryDTO;
import com.joaodss.tradeinwebsite.categorybrandspecification.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;


    // -------------------- Get Categories --------------------
    @Override
    public List<CategoryDTO> getAllCategories() {
        log.info("Getting all categories");
        return categoryRepository.findAll()
                .stream()
                .map(CategoryDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllCategoryNames() {
        log.info("Getting all category names");
        return categoryRepository.findAll()
                .stream()
                .map(Category::getCategoryName)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryById(long Id) {
        log.info("Getting category by id: {}", Id);
        return categoryRepository.findById(Id)
                .map(CategoryDTO::new)
                .orElseThrow(() -> new NoSuchElementException("Specification not found"));
    }

    @Override
    public List<CategoryDTO> getCategoryByName(String categoryName) {
        log.info("Getting category by name: {}", categoryName);
        return categoryRepository.findByCategoryName(categoryName)
                .stream()
                .map(CategoryDTO::new)
                .collect(Collectors.toList());
    }


    // -------------------- Create Category --------------------
    @Override
    public List<String> createCategories(List<String> categories) {
        log.info("Adding categories: {}", categories);
        for (String category : categories) {
            if (!categoryRepository.existsByCategoryName(category))
                categoryRepository.save(new Category(category));
        }
        return getAllCategoryNames();
    }


    // -------------------- Delete Category --------------------
    @Override
    public CategoryDTO deleteCategoryById(long id) {
        log.info("Deleting category by id: {}", id);
        CategoryDTO savedCategory = getCategoryById(id);
        categoryRepository.deleteById(id);
        return savedCategory;
    }

}
