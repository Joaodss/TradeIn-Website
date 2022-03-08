package com.joaodss.tradeinwebsite.request.dao;

import com.joaodss.tradeinwebsite.categorybrandspecification.dao.Brand;
import com.joaodss.tradeinwebsite.categorybrandspecification.dao.Category;
import com.joaodss.tradeinwebsite.request.dto.ProductDTO;
import com.joaodss.tradeinwebsite.request.enums.Condition;
import com.joaodss.tradeinwebsite.request.enums.RequestStatus;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Slf4j
public class Shoes extends Product {

    @Column(name = "shoes_size", nullable = false)
    private Short shoesSize;


    // -------------------- Custom Constructor --------------------
    public Shoes(
            RequestStatus requestStatus,
            Category category,
            Brand brand,
            String model,
            Condition condition,
            String details,
            String photosFolderURL,
            Short shoesSize
    ) {
        super(requestStatus, category, brand, model, condition, details, photosFolderURL);
        log.info("Constructing Shoes manually");
        this.shoesSize = shoesSize;
    }

    public Shoes(ProductDTO productDTO) {
        super(productDTO);
        log.info("Constructing Shoes from DTO");
        this.shoesSize = productDTO.getShoesDTO().getSize();
    }


    // -------------------- Hashcode and Equals --------------------
    @Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Shoes shoes = (Shoes) o;
        return Objects.equals(shoesSize, shoes.shoesSize);
    }

    @Generated
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), shoesSize);
    }

}
