package com.joaodss.tradeinwebsite.datatype;

import com.joaodss.tradeinwebsite.dto.TradeInRequestDTO;
import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Contact {
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;


    // -------------------- Custom Constructor --------------------
    public Contact(TradeInRequestDTO tradeInRequestDTO) {
        this.firstName = tradeInRequestDTO.getFirstName();
        this.lastName = tradeInRequestDTO.getLastName();
        this.email = tradeInRequestDTO.getEmail();
        this.mobileNumber = tradeInRequestDTO.getMobileNumber();
    }
}
