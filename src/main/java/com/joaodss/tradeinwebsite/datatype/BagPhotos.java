package com.joaodss.tradeinwebsite.datatype;

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
}
