package com.joaodss.tradeinwebsite.dto;

import com.joaodss.tradeinwebsite.enums.RequestStatus;
import com.neovisionaries.i18n.CountryCode;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ResponseTradeInRequestDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private CountryCode shippingCountry;
    private RequestStatus requestStatus;
    private BagDTO bagDTO;
    private ShoesDTO shoesDTO;
    private List<ResponseProductDTO> products;
}
