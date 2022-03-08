package com.joaodss.tradeinwebsite.categorybrandspecification.dto;

import com.joaodss.tradeinwebsite.categorybrandspecification.dao.Brand;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class BrandDTO {
    private Long id;
    private String name;


    // -------------------- Custom Constructor --------------------
    public BrandDTO(Brand brand) {
        this.id = brand.getId();
        this.name = brand.getBrandName();
    }

}
