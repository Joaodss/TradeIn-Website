package com.joaodss.tradeinwebsite.service;

import com.joaodss.tradeinwebsite.dto.specification.BrandDTO;

import java.util.List;

public interface BrandService {


    // -------------------- Get Brands --------------------
    List<BrandDTO> getAllBrands();

    List<String> getAllBrandNames();

    BrandDTO getBrandById(long Id);

    List<BrandDTO> getBrandByName(String brandName);

    // -------------------- Create Brand --------------------
    List<String> createBrands(List<String> brands);

    // -------------------- Delete Brand --------------------
    BrandDTO deleteBrandById(long id);

}
