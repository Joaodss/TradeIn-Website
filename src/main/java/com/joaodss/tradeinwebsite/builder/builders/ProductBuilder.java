package com.joaodss.tradeinwebsite.builder.builders;

import com.joaodss.tradeinwebsite.dao.Product;
import com.joaodss.tradeinwebsite.dto.ProductDTO;

public interface ProductBuilder {
    void buildFrom(ProductDTO productDTO);

    void reset();

    void setProductInformation();

    void setProductSpecificInformation();

    void setProductPhotos();

    Product getProduct();
}
