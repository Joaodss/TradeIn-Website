package com.joaodss.tradeinwebsite.dto.specification;

import com.joaodss.tradeinwebsite.dao.specification.Brand;
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
