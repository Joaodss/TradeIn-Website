package com.joaodss.tradeinwebsite.dto.request;

import com.joaodss.tradeinwebsite.dao.request.Product;
import com.joaodss.tradeinwebsite.dao.specification.Brand;
import com.joaodss.tradeinwebsite.dao.specification.Category;
import com.joaodss.tradeinwebsite.enums.Condition;
import com.joaodss.tradeinwebsite.enums.RequestStatus;
import lombok.*;


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
        this.category = category.getCategoryName();
    }

    public void setBrandFrom(Brand brand) {
        this.brand = brand.getBrandName();
    }

    public void setConditionFrom(Condition condition) {
        this.condition = condition.toString();
    }

    public void setBagDTOFrom(Product product) {
        if (product.getCategory().getCategoryName().equalsIgnoreCase("Bag"))
            this.bagDTO = new BagDTO(product);
    }

    public void setShoesDTOFrom(Product product) {
        if (product.getCategory().getCategoryName().equalsIgnoreCase("Shoes"))
            this.shoesDTO = new ShoesDTO(product);
    }

}
