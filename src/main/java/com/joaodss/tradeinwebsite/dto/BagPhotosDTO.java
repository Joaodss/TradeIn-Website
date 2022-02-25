package com.joaodss.tradeinwebsite.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class BagPhotosDTO {
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
