package com.joaodss.tradeinwebsite.request.dto;

import com.joaodss.tradeinwebsite.request.dao.Bag;
import com.joaodss.tradeinwebsite.request.dao.Product;
import com.joaodss.tradeinwebsite.request.enums.BagExtra;
import com.joaodss.tradeinwebsite.request.enums.BagSize;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class BagDTO {

    @NotNull
    @NotBlank
    private String size;

    private Set<String> extras;


    // -------------------- Custom Constructor --------------------
    public BagDTO(Product product) {
        if (product.getClass().equals(Bag.class)) {
            setSizeFrom(((Bag) product).getBagSize());
            setExtrasFrom(((Bag) product).getBagExtras());
        }
    }


    // -------------------- Custom Methods --------------------
    public void setSizeFrom(BagSize size) {
        this.size = size.toString();
    }

    public void setExtrasFrom(Set<BagExtra> extras) {
        this.extras = new HashSet<>();
        for (BagExtra extra : extras)
            this.extras.add(extra.toString());
    }

}
