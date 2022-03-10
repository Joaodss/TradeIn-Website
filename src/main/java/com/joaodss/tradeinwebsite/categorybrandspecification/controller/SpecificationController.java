package com.joaodss.tradeinwebsite.categorybrandspecification.controller;

import com.joaodss.tradeinwebsite.categorybrandspecification.dto.CategoryBrandSpecificationDTO;
import com.joaodss.tradeinwebsite.categorybrandspecification.dto.NewCategoryBrandSpecificationDTO;

import java.util.List;

public interface SpecificationController {

    // -------------------- GET Specifications --------------------
    List<CategoryBrandSpecificationDTO> getAllSpecifications();

    CategoryBrandSpecificationDTO getSpecificationsById(long id);

    CategoryBrandSpecificationDTO getSpecificationsByCategoryAndBrand(String category, String brand);


    // -------------------- POST Specifications --------------------
    CategoryBrandSpecificationDTO createSpecification(NewCategoryBrandSpecificationDTO specification);


    // -------------------- PATCH Specifications --------------------
    CategoryBrandSpecificationDTO updateSpecification(NewCategoryBrandSpecificationDTO specification);


    // -------------------- DELETE Specifications --------------------
    CategoryBrandSpecificationDTO deleteSpecificationById(long id);

}
