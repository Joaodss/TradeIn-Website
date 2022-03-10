package com.joaodss.tradeinwebsite.categorybrandspecification.service;

import com.joaodss.tradeinwebsite.categorybrandspecification.dto.CategoryBrandSpecificationDTO;
import com.joaodss.tradeinwebsite.categorybrandspecification.dto.NewCategoryBrandSpecificationDTO;

import java.util.List;

public interface SpecificationService {

    // -------------------- Get Specifications --------------------
    List<CategoryBrandSpecificationDTO> getAllSpecifications();

    CategoryBrandSpecificationDTO getSpecificationById(long Id);

    CategoryBrandSpecificationDTO getSpecificationByCategoryAndBrand(String category, String brand);

    // -------------------- Create Specifications --------------------
    CategoryBrandSpecificationDTO createSpecification(NewCategoryBrandSpecificationDTO specification);

    // -------------------- Update Specifications --------------------
    CategoryBrandSpecificationDTO updateSpecification(NewCategoryBrandSpecificationDTO specification);

    // -------------------- Delete Specifications --------------------
    CategoryBrandSpecificationDTO deleteSpecificationById(long id);

}
