package com.joaodss.tradeinwebsite.categorybrandspecification.dto;

import com.joaodss.tradeinwebsite.categorybrandspecification.dao.CategoryBrandSpecification;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CategoryBrandSpecificationDTO {
    private Long id;
    private String category;
    private String brand;
    private List<String> mandatoryExternalPhotos;
    private List<String> externalPhotos;
    private List<String> mandatoryInternalPhotos;
    private List<String> internalPhotos;


    // -------------------- Custom Constructor --------------------
    public CategoryBrandSpecificationDTO(CategoryBrandSpecification specification) {
        this.id = specification.getId();
        this.category = specification.getCategory().getCategoryName();
        this.brand = specification.getBrand().getBrandName();
        this.mandatoryExternalPhotos = new ArrayList<>(specification.getMandatoryExternalPhotos());
        this.externalPhotos = new ArrayList<>(specification.getExternalPhotos());
        this.mandatoryInternalPhotos = new ArrayList<>(specification.getMandatoryInternalPhotos());
        this.internalPhotos = new ArrayList<>(specification.getInternalPhotos());
    }

}
