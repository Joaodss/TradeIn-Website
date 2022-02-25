package com.joaodss.tradeinwebsite.datatype;

import com.joaodss.tradeinwebsite.dto.ShoesDTO;
import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ShoesPhotos {
    private String externalTopURL;
    private String externalLeftSideURL;
    private String externalRightSideURL;
    private String externalBottomURL;
    private String externalLogoURL;
    private String externalSerialURL;
    private String externalZipperURL;
    private String externalHardwareURL;

    private String internalInsideURL;
    private String internalLogoURL;
    private String internalSerialURL;


    // -------------------- Custom Constructor --------------------
    public ShoesPhotos(ShoesDTO shoesDTO) {
        this.externalTopURL = shoesDTO.getExternalTopURL();
        this.externalLeftSideURL = shoesDTO.getExternalLeftSideURL();
        this.externalRightSideURL = shoesDTO.getExternalRightSideURL();
        this.externalBottomURL = shoesDTO.getExternalBottomURL();
        this.externalLogoURL = shoesDTO.getExternalLogoURL();
        this.externalSerialURL = shoesDTO.getExternalSerialURL();
        this.externalZipperURL = shoesDTO.getExternalZipperURL();
        this.externalHardwareURL = shoesDTO.getExternalHardwareURL();
        this.internalInsideURL = shoesDTO.getInternalInsideURL();
        this.internalLogoURL = shoesDTO.getInternalLogoURL();
        this.internalSerialURL = shoesDTO.getInternalSerialURL();
    }

}
