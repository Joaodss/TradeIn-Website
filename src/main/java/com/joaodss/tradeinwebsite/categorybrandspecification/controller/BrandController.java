package com.joaodss.tradeinwebsite.categorybrandspecification.controller;

import com.joaodss.tradeinwebsite.categorybrandspecification.dto.BrandDTO;

import java.util.List;

public interface BrandController {

    // -------------------- Get Brands --------------------
    List<BrandDTO> getAllBrands();

    List<String> getAllBrandNames();

    BrandDTO getBrandById(long Id);

    List<BrandDTO> getBrandByName(String brandName);


    // -------------------- Post Brand --------------------
    List<String> createBrands(List<String> brands);


    // -------------------- Delete Brand --------------------
    BrandDTO deleteBrandById(long id);

}
