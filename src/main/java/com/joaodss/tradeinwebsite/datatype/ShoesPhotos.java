package com.joaodss.tradeinwebsite.datatype;

import com.joaodss.tradeinwebsite.dto.ShoesPhotosDTO;
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
    public ShoesPhotos(ShoesPhotosDTO shoesPhotosDTO) {
        this.externalTopURL = shoesPhotosDTO.getExternalTopURL();
        this.externalLeftSideURL = shoesPhotosDTO.getExternalLeftSideURL();
        this.externalRightSideURL = shoesPhotosDTO.getExternalRightSideURL();
        this.externalBottomURL = shoesPhotosDTO.getExternalBottomURL();
        this.externalLogoURL = shoesPhotosDTO.getExternalLogoURL();
        this.externalSerialURL = shoesPhotosDTO.getExternalSerialURL();
        this.externalZipperURL = shoesPhotosDTO.getExternalZipperURL();
        this.externalHardwareURL = shoesPhotosDTO.getExternalHardwareURL();
        this.internalInsideURL = shoesPhotosDTO.getInternalInsideURL();
        this.internalLogoURL = shoesPhotosDTO.getInternalLogoURL();
        this.internalSerialURL = shoesPhotosDTO.getInternalSerialURL();
    }

}
