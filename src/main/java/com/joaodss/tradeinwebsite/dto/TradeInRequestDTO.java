package com.joaodss.tradeinwebsite.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @NotNull(message = "Email is required")
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email")
    private String email;

    private String mobileNumber;

    @NotNull(message = "Sipping Country ISO Code is required")
    @Size(min = 2, max = 9, message = "Invalid ISO Code")
    private String shippingCountryISOCode;

    @Size(min = 1, message = "Must have at least one product")
    private List<ProductDTO> products;

}
