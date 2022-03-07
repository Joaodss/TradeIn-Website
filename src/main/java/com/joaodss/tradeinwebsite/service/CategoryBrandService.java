package com.joaodss.tradeinwebsite.service;

import com.joaodss.tradeinwebsite.dao.specification.Brand;
import com.joaodss.tradeinwebsite.dao.specification.Category;
import com.joaodss.tradeinwebsite.dto.specification.BrandDTO;
import com.joaodss.tradeinwebsite.dto.specification.CategoryDTO;
import com.joaodss.tradeinwebsite.dto.specification.CategoryBrandSpecificationDTO;
import com.joaodss.tradeinwebsite.dto.specification.NewCategoryBrandSpecificationDTO;

import java.util.List;

public interface CategoryBrandService {

    // -------------------- Get Specifications --------------------
    List<CategoryBrandSpecificationDTO> getAllSpecifications();

    CategoryBrandSpecificationDTO getSpecificationsById(Long Id);

    CategoryBrandSpecificationDTO getSpecificationsByCategoryAndBrand(String category, String brand);


    // -------------------- Get Categories --------------------
    List<String> getAllCategories();

    Category getCategoryBtName(String categoryName);


    // -------------------- Get Brands --------------------
    List<String> getAllBrands();

    Brand getBrandByName(String brandName);


    // -------------------- Post --------------------
    CategoryBrandSpecificationDTO addSpecification(NewCategoryBrandSpecificationDTO specification);

    List<String> addCategories(List<String> categories);

    List<String> addBrands(List<String> brands);


//    // -------------------- Put --------------------
//    CategoryBrandSpecificationDTO updateSpecification(CategoryBrandSpecificationDTO specification);


    // -------------------- Patch --------------------
    CategoryBrandSpecificationDTO updateSpecification(NewCategoryBrandSpecificationDTO specification);


    // -------------------- Delete --------------------
    CategoryBrandSpecificationDTO deleteSpecificationById(long id);

    CategoryDTO deleteCategoryById(long id);

    BrandDTO deleteBrandById(long id);


}
