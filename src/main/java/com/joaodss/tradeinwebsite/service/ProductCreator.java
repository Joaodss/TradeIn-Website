package com.joaodss.tradeinwebsite.service;

import com.joaodss.tradeinwebsite.dao.Product;
import com.joaodss.tradeinwebsite.dto.ProductDTO;

public class ProductCreator {

    public Product createProductFrom(ProductDTO productDTO) {
        switch (productDTO.getCategory()) {
            case "bag" -> {
                ProductBuilder bagBuilder = new BagBuilder();
                bagBuilder.buildFrom(productDTO);
                return bagBuilder.getProduct();
            }
            case "shoes" -> {
                ProductBuilder shoesBuilder = new ShoesBuilder();
                shoesBuilder.buildFrom(productDTO);
                return shoesBuilder.getProduct();
            }
            default -> throw new IllegalArgumentException();
            // TODO: return error or return null?
        }
    }


}
