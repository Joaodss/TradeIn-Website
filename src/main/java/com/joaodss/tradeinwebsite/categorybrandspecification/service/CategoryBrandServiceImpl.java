package com.joaodss.tradeinwebsite.categorybrandspecification.service;

import com.joaodss.tradeinwebsite.categorybrandspecification.dao.CategoryBrandSpecification;
import com.joaodss.tradeinwebsite.categorybrandspecification.dto.CategoryBrandSpecificationDTO;
import com.joaodss.tradeinwebsite.categorybrandspecification.dto.NewCategoryBrandSpecificationDTO;
import com.joaodss.tradeinwebsite.categorybrandspecification.repository.CategoryBrandSpecificationRepository;
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
public class CategoryBrandServiceImpl implements CategoryBrandService {
    private final CategoryBrandSpecificationRepository categoryBrandSpecificationRepository;


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
    public CategoryBrandSpecificationDTO getSpecificationsById(long id) {
        log.info("Getting specification by id: {}", id);
        return categoryBrandSpecificationRepository.findById(id)
                .map(CategoryBrandSpecificationDTO::new)
                .orElseThrow(() -> new NoSuchElementException("Specification not found"));
    }

    @Override
    public CategoryBrandSpecificationDTO getSpecificationsByCategoryAndBrand(String category, String brand) {
        log.info("Getting specification by category: {} and brand: {}", category, brand);
        return categoryBrandSpecificationRepository.findByCategory_CategoryNameAndBrand_BrandName(category, brand)
                .map(CategoryBrandSpecificationDTO::new)
                .orElseThrow(() -> new NoSuchElementException("Specification not found"));
    }


    // -------------------- Create Specification --------------------
    @Override
    public CategoryBrandSpecificationDTO createSpecification(NewCategoryBrandSpecificationDTO specification) {
        log.info("Adding specification: {}", specification);
        var savedSpecification = categoryBrandSpecificationRepository
                .save(new CategoryBrandSpecification(specification));
        return getSpecificationsById(savedSpecification.getId());
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
        return getSpecificationsById(savedSpecification.getId());
    }


    // -------------------- Delete Specifications --------------------
    @Override
    public CategoryBrandSpecificationDTO deleteSpecificationById(long id) {
        log.info("Deleting specification by id: {}", id);
        CategoryBrandSpecificationDTO savedSpecification = getSpecificationsById(id);
        categoryBrandSpecificationRepository.deleteById(id);
        return savedSpecification;
    }

}
