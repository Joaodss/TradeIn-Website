package com.joaodss.tradeinwebsite.dto;

import lombok.*;

import java.util.Set;

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
}
