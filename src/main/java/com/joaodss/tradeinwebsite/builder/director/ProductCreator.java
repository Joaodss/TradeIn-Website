package com.joaodss.tradeinwebsite.builder.director;

import com.joaodss.tradeinwebsite.builder.builders.BagBuilder;
import com.joaodss.tradeinwebsite.builder.builders.ProductBuilder;
import com.joaodss.tradeinwebsite.builder.builders.ShoesBuilder;
import com.joaodss.tradeinwebsite.dao.Product;
import com.joaodss.tradeinwebsite.dto.ProductDTO;
import com.joaodss.tradeinwebsite.enums.Category;
import lombok.NoArgsConstructor;

import static com.joaodss.tradeinwebsite.utils.EnumsUtil.enumFormat;

@NoArgsConstructor
public class ProductCreator {

    public Product createProductFrom(ProductDTO productDTO) {
        switch (enumFormat(productDTO.getCategory())) {
            case "BAG" -> {
                ProductBuilder bagBuilder = new BagBuilder();
                bagBuilder.buildFrom(productDTO);
                return bagBuilder.getProduct();
            }
            case "SHOES" -> {
                ProductBuilder shoesBuilder = new ShoesBuilder();
                shoesBuilder.buildFrom(productDTO);
                return shoesBuilder.getProduct();
            }
            default -> throw new IllegalArgumentException("Category: " + productDTO.getCategory() + ", does not exist.");
            // TODO: return error or return null?
        }
    }


}
