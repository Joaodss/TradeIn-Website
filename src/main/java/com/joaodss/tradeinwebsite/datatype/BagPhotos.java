package com.joaodss.tradeinwebsite.datatype;

import com.joaodss.tradeinwebsite.dto.BagPhotosDTO;
import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class BagPhotos {
    private String externalFrontURL;
    private String externalBackURL;
    private String externalBottomURL;
    private String externalLogoURL;
    private String externalSerialURL;
    private String externalZipperURL;
    private String externalHardwareURL;

    private String internalInsideURL;
    private String internalBottomURL;
    private String internalLogoURL;
    private String internalSerialURL;
    private String internalZipperURL;
    private String internalHardwareURL;


    // -------------------- Custom Constructor --------------------
    public BagPhotos(BagPhotosDTO bagPhotosDTO) {
        this.externalFrontURL = bagPhotosDTO.getExternalFrontURL();
        this.externalBackURL = bagPhotosDTO.getExternalBackURL();
        this.externalBottomURL = bagPhotosDTO.getExternalBottomURL();
        this.externalLogoURL = bagPhotosDTO.getExternalLogoURL();
        this.externalSerialURL = bagPhotosDTO.getExternalSerialURL();
        this.externalZipperURL = bagPhotosDTO.getExternalZipperURL();
        this.externalHardwareURL = bagPhotosDTO.getExternalHardwareURL();
        this.internalInsideURL = bagPhotosDTO.getInternalInsideURL();
        this.internalBottomURL = bagPhotosDTO.getInternalBottomURL();
        this.internalLogoURL = bagPhotosDTO.getInternalLogoURL();
        this.internalSerialURL = bagPhotosDTO.getInternalSerialURL();
        this.internalZipperURL = bagPhotosDTO.getInternalZipperURL();
        this.internalHardwareURL = bagPhotosDTO.getInternalHardwareURL();
    }

}
