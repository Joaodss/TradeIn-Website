package com.joaodss.tradeinwebsite.request.builder;

import com.joaodss.tradeinwebsite.request.dao.Bag;
import com.joaodss.tradeinwebsite.request.dao.Product;
import com.joaodss.tradeinwebsite.request.dao.Shoes;
import com.joaodss.tradeinwebsite.categorybrandspecification.dao.Brand;
import com.joaodss.tradeinwebsite.categorybrandspecification.dao.Category;
import com.joaodss.tradeinwebsite.request.dto.ProductDTO;
import com.joaodss.tradeinwebsite.categorybrandspecification.repository.BrandRepository;
import com.joaodss.tradeinwebsite.categorybrandspecification.repository.CategoryRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import static com.joaodss.tradeinwebsite.utils.EnumsUtil.enumFormat;

@NoArgsConstructor
@Getter
@Setter
@Slf4j
public class ProductBuilder {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BrandRepository brandRepository;

    private Product product;


    public ProductBuilder buildProductFrom(ProductDTO productDTO) {
        log.info("Building product from DTO");
        switch (enumFormat(productDTO.getCategory())) {
            case "BAG" -> this.product = new Bag(productDTO);
            case "SHOES" -> this.product = new Shoes(productDTO);
            default -> throw new IllegalArgumentException("Category: " + productDTO.getCategory() + ", does not exist.");
        }
        return this;
    }

    public ProductBuilder setProductCategory(String category) {
        log.info("Setting product category");
        Category savedCategory = categoryRepository.findByCategoryName(category)
                .stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Category: " + category + ", does not exist."));
        this.product.setCategory(savedCategory);
        return this;
    }

    public ProductBuilder setProductBrand(String brand) {
        log.info("Setting product brand");
        Brand savedBrand = brandRepository.findByBrandName(brand)
                .stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Brand: " + brand + ", does not exist."));
        this.product.setBrand(savedBrand);
        return this;
    }

    public Product build() {
        return product;
    }

}