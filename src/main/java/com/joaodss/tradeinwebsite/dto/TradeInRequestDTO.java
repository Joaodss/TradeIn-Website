package com.joaodss.tradeinwebsite.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class TradeInRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String shippingCountryISOCode;
    private List<ProductDTO> products;
}
