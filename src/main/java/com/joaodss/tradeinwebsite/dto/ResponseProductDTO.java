package com.joaodss.tradeinwebsite.dto;

import com.joaodss.tradeinwebsite.dao.Product;
import com.joaodss.tradeinwebsite.enums.Brand;
import com.joaodss.tradeinwebsite.enums.Category;
import com.joaodss.tradeinwebsite.enums.Condition;
import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ResponseProductDTO {
    private Long id;
    private String category;
    private String brand;
    private String model;
    private String condition;
    private String details;
    private BagDTO bagDTO;
    private ShoesDTO shoesDTO;
    private List<String> blemishPhotos;


    // -------------------- Custom Constructor --------------------
    public ResponseProductDTO(Product product) {
        this.id = product.getId();
        setCategory(product.getCategory());
        setBrand(product.getBrand());
        this.model = product.getModel();
        setCondition(product.getCondition());
        this.details = product.getDetails();
        this.bagDTO = new BagDTO(product);
        this.shoesDTO = new ShoesDTO(product);
        this.blemishPhotos = product.getBlemishPhotos();
    }


    // -------------------- Custom Methods --------------------
    public void setCategory(Category category) {
        this.category = category.toString();
    }

    private void setBrand(Brand brand) {
        this.brand = brand.toString();
    }

    private void setCondition(Condition condition) {
        this.condition = condition.toString();
    }

}
