package com.joaodss.tradeinwebsite.categorybrandspecification.service.impl;

import com.joaodss.tradeinwebsite.categorybrandspecification.dao.Brand;
import com.joaodss.tradeinwebsite.categorybrandspecification.dao.Category;
import com.joaodss.tradeinwebsite.categorybrandspecification.dao.CategoryBrandSpecification;
import com.joaodss.tradeinwebsite.categorybrandspecification.dto.CategoryBrandSpecificationDTO;
import com.joaodss.tradeinwebsite.categorybrandspecification.dto.NewCategoryBrandSpecificationDTO;
import com.joaodss.tradeinwebsite.categorybrandspecification.repository.BrandRepository;
import com.joaodss.tradeinwebsite.categorybrandspecification.repository.CategoryBrandSpecificationRepository;
import com.joaodss.tradeinwebsite.categorybrandspecification.repository.CategoryRepository;
import com.joaodss.tradeinwebsite.categorybrandspecification.service.SpecificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SpecificationServiceImpl implements SpecificationService {
    private final CategoryBrandSpecificationRepository categoryBrandSpecificationRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;


    // -------------------- Get Specifications --------------------
    @Override
    public List<CategoryBrandSpecificationDTO> getAllSpecifications() {
        log.info("Getting all specifications");
        return categoryBrandSpecificationRepository.findAll()
                .stream()
                .map(CategoryBrandSpecificationDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryBrandSpecificationDTO getSpecificationById(long id) {
        log.info("Getting specification by id: {}", id);
        return categoryBrandSpecificationRepository.findById(id)
                .map(CategoryBrandSpecificationDTO::new)
                .orElseThrow(() -> new NoSuchElementException("Specification not found"));
    }

    @Override
    public CategoryBrandSpecificationDTO getSpecificationByCategoryAndBrand(String category, String brand) {
        log.info("Getting specification by category: {} and brand: {}", category, brand);
        return categoryBrandSpecificationRepository.findByCategory_CategoryNameAndBrand_BrandName(category, brand)
                .map(CategoryBrandSpecificationDTO::new)
                .orElseThrow(() -> new NoSuchElementException("Specification not found"));
    }


    // -------------------- Create Specification --------------------
    @Override
    public CategoryBrandSpecificationDTO createSpecification(NewCategoryBrandSpecificationDTO specification) {
        log.info("Adding specification: {}", specification);
        if (categoryBrandSpecificationRepository.existsByCategory_CategoryNameAndBrand_BrandName(
                specification.getCategory(), specification.getBrand())) {
            log.info("Specification already exists");
            throw new IllegalArgumentException("Specification already exists");
        }
        CategoryBrandSpecification newSpecification = new CategoryBrandSpecification(specification);
        newSpecification.setCategory(setSpecificationCategory(specification.getCategory()));
        newSpecification.setBrand(setSpecificationBrand(specification.getBrand()));

        CategoryBrandSpecification savedSpecification = categoryBrandSpecificationRepository.save(newSpecification);
        return getSpecificationById(savedSpecification.getId());
    }

    private Category setSpecificationCategory(String categoryName) {
        log.info("Setting specification category: {}", categoryName);
        Category existingCategory = getCategoryByName(categoryName);
        if (existingCategory == null)
            existingCategory = createCategory(categoryName);
        return existingCategory;
    }

    private Category getCategoryByName(String categoryName) {
        log.info("Getting category by name: {}", categoryName);
        return categoryRepository.findByCategoryName(categoryName)
                .stream()
                .findFirst()
                .orElse(null);
    }

    private Category createCategory(String categoryName) {
        log.info("Creating category by name: {}", categoryName);
        return categoryRepository.save(new Category(categoryName));
    }

    private Brand setSpecificationBrand(String brandName) {
        log.info("Setting specification brang: {}", brandName);
        Brand existingBrand = getBrandByName(brandName);
        if (existingBrand == null)
            existingBrand = createBrand(brandName);
        return existingBrand;
    }

    private Brand getBrandByName(String brandName) {
        log.info("Getting brand by name: {}", brandName);
        return brandRepository.findByBrandName(brandName)
                .stream()
                .findFirst()
                .orElse(null);
    }

    private Brand createBrand(String brandName) {
        log.info("Creating brand by name: {}", brandName);
        return brandRepository.save(new Brand(brandName));
    }


    // -------------------- Update Specifications --------------------
    @Override
    public CategoryBrandSpecificationDTO updateSpecification(NewCategoryBrandSpecificationDTO specification) {
        log.info("Updating specification: {}", specification);
        CategoryBrandSpecification savedSpecification = categoryBrandSpecificationRepository
                .findByCategory_CategoryNameAndBrand_BrandName(specification.getCategory(), specification.getBrand())
                .orElseThrow(() -> new NoSuchElementException("Specification not found"));
        if (specification.getMandatoryExternalPhotos() != null)
            savedSpecification.setMandatoryExternalPhotos(new HashSet<>(specification.getMandatoryExternalPhotos()));
        if (specification.getExternalPhotos() != null)
            savedSpecification.setExternalPhotos(new HashSet<>(specification.getExternalPhotos()));
        if (specification.getMandatoryInternalPhotos() != null)
            savedSpecification.setMandatoryInternalPhotos(new HashSet<>(specification.getMandatoryInternalPhotos()));
        if (specification.getInternalPhotos() != null)
            savedSpecification.setInternalPhotos(new HashSet<>(specification.getInternalPhotos()));
        categoryBrandSpecificationRepository.save(savedSpecification);
        return getSpecificationById(savedSpecification.getId());
    }


    // -------------------- Delete Specifications --------------------
    @Override
    public CategoryBrandSpecificationDTO deleteSpecificationById(long id) {
        log.info("Deleting specification by id: {}", id);
        CategoryBrandSpecificationDTO savedSpecification = getSpecificationById(id);
        categoryBrandSpecificationRepository.deleteById(id);
        return savedSpecification;
    }

}
