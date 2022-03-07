package com.joaodss.tradeinwebsite.service;

import com.joaodss.tradeinwebsite.dto.specification.CategoryBrandSpecificationDTO;
import com.joaodss.tradeinwebsite.dto.specification.NewCategoryBrandSpecificationDTO;

import java.util.List;

public interface CategoryBrandService {

    // -------------------- Get Specifications --------------------
    List<CategoryBrandSpecificationDTO> getAllSpecifications();

    CategoryBrandSpecificationDTO getSpecificationsById(long Id);

    CategoryBrandSpecificationDTO getSpecificationsByCategoryAndBrand(String category, String brand);

    // -------------------- Create Specifications --------------------
    CategoryBrandSpecificationDTO createSpecification(NewCategoryBrandSpecificationDTO specification);

    // -------------------- Update Specifications --------------------
    CategoryBrandSpecificationDTO updateSpecification(NewCategoryBrandSpecificationDTO specification);

    // -------------------- Delete Specifications --------------------
    CategoryBrandSpecificationDTO deleteSpecificationById(long id);

}
