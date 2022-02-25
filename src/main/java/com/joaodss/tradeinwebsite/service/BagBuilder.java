package com.joaodss.tradeinwebsite.service;

import com.joaodss.tradeinwebsite.dao.Bag;
import com.joaodss.tradeinwebsite.dao.Product;
import com.joaodss.tradeinwebsite.dto.ProductDTO;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
public class BagBuilder implements ProductBuilder {
    private Bag bag;
    private ProductDTO productDTO;


    // -------------------- Constructor --------------------
    public BagBuilder(ProductDTO productDTO) {
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
        bag = new Bag();
    }

    @Override
    public void setProductInformation() {
        bag.setCategoryFrom(productDTO.getCategory());
        bag.setBrandFrom(productDTO.getBrand());
        bag.setModel(productDTO.getModel());
        bag.setConditionFrom(productDTO.getCondition());
        bag.setDetails(productDTO.getDetails());
        bag.setBlemishPhotos(productDTO.getBlemishPhotos());
    }

    @Override
    public void setProductSpecificInformation() {
        bag.setBagSizeFrom(productDTO.getBagDTO().getSize());
        bag.setBagExtrasFrom(productDTO.getBagDTO().getExtras());
    }

    // TODO: Create photo builder
    @Override
    public void setProductPhotos() {

    }

    @Override
    public Product getProduct() {
        return bag;
    }
}
