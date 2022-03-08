package com.joaodss.tradeinwebsite.categorybrandspecification.service;

import com.joaodss.tradeinwebsite.categorybrandspecification.dao.Brand;
import com.joaodss.tradeinwebsite.categorybrandspecification.dto.BrandDTO;
import com.joaodss.tradeinwebsite.categorybrandspecification.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;


    // -------------------- Get Brands --------------------
    @Override
    public List<BrandDTO> getAllBrands() {
        log.info("Getting all brands");
        return brandRepository.findAll()
                .stream()
                .map(BrandDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllBrandNames() {
        log.info("Getting all brand names");
        return brandRepository.findAll()
                .stream()
                .map(Brand::getBrandName)
                .collect(Collectors.toList());
    }

    @Override
    public BrandDTO getBrandById(long Id) {
        log.info("Getting brand by id: {}", Id);
        return brandRepository.findById(Id)
                .map(BrandDTO::new)
                .orElseThrow(() -> new NoSuchElementException("Specification not found"));
    }

    @Override
    public List<BrandDTO> getBrandByName(String brandName) {
        log.info("Getting brand by name: {}", brandName);
        return brandRepository.findByBrandName(brandName)
                .stream()
                .map(BrandDTO::new)
                .collect(Collectors.toList());
    }


    // -------------------- Create Brand --------------------
    @Override
    public List<String> createBrands(List<String> brands) {
        log.info("Adding brands: {}", brands);
        for (String brand : brands) {
            if (!brandRepository.existsByBrandName(brand))
                brandRepository.save(new Brand(brand));
        }
        return getAllBrandNames();
    }


    // -------------------- Delete Brand --------------------
    @Override
    public BrandDTO deleteBrandById(long id) {
        log.info("Deleting brand by id: {}", id);
        BrandDTO savedBrand = getBrandById(id);
        brandRepository.deleteById(id);
        return savedBrand;
    }

}
