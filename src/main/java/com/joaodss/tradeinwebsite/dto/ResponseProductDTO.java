package com.joaodss.tradeinwebsite.dto;

import com.joaodss.tradeinwebsite.dao.Product;
import com.joaodss.tradeinwebsite.enums.Brand;
import com.joaodss.tradeinwebsite.enums.Category;
import com.joaodss.tradeinwebsite.enums.Condition;
import com.joaodss.tradeinwebsite.enums.RequestStatus;
import lombok.*;

import static com.joaodss.tradeinwebsite.enums.Category.BAG;
import static com.joaodss.tradeinwebsite.enums.Category.SHOES;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ResponseProductDTO {
    private Long id;
    private String requestStatus;
    private String category;
    private String brand;
    private String model;
    private String condition;
    private String details;
    private String photosFolderURL;
    private BagDTO bagDTO;
    private ShoesDTO shoesDTO;


    // -------------------- Custom Constructor --------------------
    public ResponseProductDTO(Product product) {
        this.id = product.getId();
        setRequestStatusFrom(product.getRequestStatus());
        setCategoryFrom(product.getCategory());
        setBrandFrom(product.getBrand());
        this.model = product.getModel();
        setConditionFrom(product.getCondition());
        this.details = product.getDetails();
        setBagDTOFrom(product);
        setShoesDTOFrom(product);
        this.photosFolderURL = product.getPhotosFolderURL();
    }


    // -------------------- Custom Methods --------------------
    private void setRequestStatusFrom(RequestStatus requestStatus) {
        this.requestStatus = requestStatus.toString();
    }

    public void setCategoryFrom(Category category) {
        this.category = category.toString();
    }

    public void setBrandFrom(Brand brand) {
        this.brand = brand.toString();
    }

    public void setConditionFrom(Condition condition) {
        this.condition = condition.toString();
    }

    public void setBagDTOFrom(Product product) {
        if (product.getCategory().equals(BAG))
            this.bagDTO = new BagDTO(product);
    }

    public void setShoesDTOFrom(Product product) {
        if (product.getCategory().equals(SHOES))
            this.shoesDTO = new ShoesDTO(product);
    }

}
