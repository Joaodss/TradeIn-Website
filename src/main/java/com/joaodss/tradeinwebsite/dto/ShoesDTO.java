package com.joaodss.tradeinwebsite.dto;

import com.joaodss.tradeinwebsite.dao.Product;
import com.joaodss.tradeinwebsite.dao.Shoes;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ShoesDTO {
    private Short size;

//    private String externalTopURL;
//    private String externalLeftSideURL;
//    private String externalRightSideURL;
//    private String externalBottomURL;
//    private String externalLogoURL;
//    private String externalSerialURL;
//    private String externalZipperURL;
//    private String externalHardwareURL;
//    private String internalInsideURL;
//    private String internalLogoURL;
//    private String internalSerialURL;


    public ShoesDTO(Product product) {
        if (product.getClass().equals(Shoes.class)) {
            this.size = ((Shoes) product).getShoesSize();
        }
    }

}
