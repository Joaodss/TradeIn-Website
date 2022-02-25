package com.joaodss.tradeinwebsite.builder.director;

import com.joaodss.tradeinwebsite.builder.builders.BagBuilder;
import com.joaodss.tradeinwebsite.builder.builders.ProductBuilder;
import com.joaodss.tradeinwebsite.builder.builders.ShoesBuilder;
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
