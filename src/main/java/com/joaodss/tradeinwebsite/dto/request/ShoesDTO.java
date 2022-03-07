package com.joaodss.tradeinwebsite.dto.request;

import com.joaodss.tradeinwebsite.dao.request.Product;
import com.joaodss.tradeinwebsite.dao.request.Shoes;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ShoesDTO {

    @NotNull
    @Positive
    private Short size;


    // -------------------- Custom Constructor --------------------
    public ShoesDTO(Product product) {
        if (product.getClass().equals(Shoes.class)) {
            this.size = ((Shoes) product).getShoesSize();
        }
    }

}
