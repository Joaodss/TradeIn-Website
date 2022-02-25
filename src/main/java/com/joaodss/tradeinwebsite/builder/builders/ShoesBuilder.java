package com.joaodss.tradeinwebsite.builder.builders;

import com.joaodss.tradeinwebsite.dao.Product;
import com.joaodss.tradeinwebsite.dao.Shoes;
import com.joaodss.tradeinwebsite.dto.ProductDTO;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
public class ShoesBuilder implements ProductBuilder {
    private Shoes shoes;
    private ProductDTO productDTO;


    // -------------------- Constructor --------------------
    public ShoesBuilder(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }


    // -------------------- Build Methods --------------------
    @Override
    public void buildFrom(ProductDTO productDTO) {
        this.productDTO = productDTO;
        reset();
        setProductInformation();
        setProductSpecificInformation();
        setProductPhotos();
    }

    @Override
    public void reset() {
        shoes = new Shoes();
    }

    @Override
    public void setProductInformation() {
        shoes.setCategoryFrom(productDTO.getCategory());
        shoes.setBrandFrom(productDTO.getBrand());
        shoes.setModel(productDTO.getModel());
        shoes.setConditionFrom(productDTO.getCondition());
        shoes.setDetails(productDTO.getDetails());
        shoes.setBlemishPhotos(productDTO.getBlemishPhotos());
    }

    @Override
    public void setProductSpecificInformation() {
        shoes.setShoesSize(productDTO.getShoesDTO().getSize());
    }

    // TODO: Create photo builder
    @Override
    public void setProductPhotos() {

    }

    @Override
    public Product getProduct() {
        return shoes;
    }
}
