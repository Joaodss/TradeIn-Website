package com.joaodss.tradeinwebsite.builder;

import com.joaodss.tradeinwebsite.dao.Bag;
import com.joaodss.tradeinwebsite.dao.Product;
import com.joaodss.tradeinwebsite.dao.Shoes;
import com.joaodss.tradeinwebsite.dto.ProductDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.joaodss.tradeinwebsite.utils.EnumsUtil.enumFormat;

@NoArgsConstructor
@Getter
@Setter
public class ProductBuilder {
    private Product product;

    public ProductBuilder buildProductFrom(ProductDTO productDTO) {
        switch (enumFormat(productDTO.getCategory())) {
            case "BAG" -> this.product = new Bag(productDTO);
            case "SHOES" -> this.product = new Shoes(productDTO);
            default -> throw new IllegalArgumentException("Category: " + productDTO.getCategory() + ", does not exist.");
            // TODO: return error or return null?
        }
        return this;
    }

    public Product build() {
        return product;
    }

}
