package com.joaodss.tradeinwebsite.datatype;

import com.joaodss.tradeinwebsite.dto.BagDTO;
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
    public BagPhotos(BagDTO bagDTO) {
        this.externalFrontURL = bagDTO.getExternalFrontURL();
        this.externalBackURL = bagDTO.getExternalBackURL();
        this.externalBottomURL = bagDTO.getExternalBottomURL();
        this.externalLogoURL = bagDTO.getExternalLogoURL();
        this.externalSerialURL = bagDTO.getExternalSerialURL();
        this.externalZipperURL = bagDTO.getExternalZipperURL();
        this.externalHardwareURL = bagDTO.getExternalHardwareURL();
        this.internalInsideURL = bagDTO.getInternalInsideURL();
        this.internalBottomURL = bagDTO.getInternalBottomURL();
        this.internalLogoURL = bagDTO.getInternalLogoURL();
        this.internalSerialURL = bagDTO.getInternalSerialURL();
        this.internalZipperURL = bagDTO.getInternalZipperURL();
        this.internalHardwareURL = bagDTO.getInternalHardwareURL();
    }

}
