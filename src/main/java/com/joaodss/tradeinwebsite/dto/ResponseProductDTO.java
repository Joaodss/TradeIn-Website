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
        setRequestStatus(product.getRequestStatus());
        setCategory(product.getCategory());
        setBrand(product.getBrand());
        this.model = product.getModel();
        setCondition(product.getCondition());
        this.details = product.getDetails();
        setBagDTO(product);
        setShoesDTO(product);
        this.photosFolderURL = product.getPhotosFolderURL();
    }


    // -------------------- Custom Methods --------------------
    private void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus.toString();
    }

    public void setCategory(Category category) {
        this.category = category.toString();
    }

    public void setBrand(Brand brand) {
        this.brand = brand.toString();
    }

    public void setCondition(Condition condition) {
        this.condition = condition.toString();
    }

    public void setBagDTO(Product product) {
        if (product.getCategory().equals(BAG))
            this.bagDTO = new BagDTO(product);
    }

    public void setShoesDTO(Product product) {
        if (product.getCategory().equals(SHOES))
            this.shoesDTO = new ShoesDTO(product);
    }

}
