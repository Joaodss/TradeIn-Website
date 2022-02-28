package com.joaodss.tradeinwebsite.dto;

import com.joaodss.tradeinwebsite.dao.Bag;
import com.joaodss.tradeinwebsite.dao.Product;
import com.joaodss.tradeinwebsite.enums.BagExtra;
import com.joaodss.tradeinwebsite.enums.BagSize;
import lombok.*;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class BagDTO {
    private String size;
    private Set<String> extras;

//    private String externalFrontURL;
//    private String externalBackURL;
//    private String externalBottomURL;
//    private String externalLogoURL;
//    private String externalSerialURL;
//    private String externalZipperURL;
//    private String externalHardwareURL;
//    private String internalInsideURL;
//    private String internalBottomURL;
//    private String internalLogoURL;
//    private String internalSerialURL;
//    private String internalZipperURL;
//    private String internalHardwareURL;


    // -------------------- Custom Constructor --------------------
    public BagDTO(Product product) {
        if (product.getClass().equals(Bag.class)) {
            setSize(((Bag) product).getBagSize());
            setExtras(((Bag) product).getBagExtras());
        }
    }


    // -------------------- Custom Methods --------------------
    public void setSize(BagSize size) {
        this.size = size.toString();
    }

    public void setExtras(Set<BagExtra> extras) {
        this.extras = extras.stream()
                .map(Enum::toString)
                .collect(Collectors.toSet());
    }

}
