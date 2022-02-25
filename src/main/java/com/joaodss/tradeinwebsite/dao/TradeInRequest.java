package com.joaodss.tradeinwebsite.dao;

import com.joaodss.tradeinwebsite.dto.TradeInRequestDTO;
import com.joaodss.tradeinwebsite.enums.RequestStatus;
import com.neovisionaries.i18n.CountryCode;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Trade_in_request")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Slf4j
public class TradeInRequest {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "shipping_country", nullable = false)
    private CountryCode shippingCountry;

    @Enumerated(STRING)
    @Column(name = "request_status", nullable = false)
    private RequestStatus requestStatus;

    @ToString.Exclude
    @OneToMany(mappedBy = "tradeInRequest")
    private List<Product> products = new ArrayList<>();


    // -------------------- Custom Constructor --------------------
    public TradeInRequest(TradeInRequestDTO tradeInRequestDTO) {
        this.firstName = tradeInRequestDTO.getFirstName();
        this.lastName = tradeInRequestDTO.getLastName();
        this.email = tradeInRequestDTO.getEmail();
        this.mobileNumber = tradeInRequestDTO.getMobileNumber();
        this.setShippingCountryFrom(tradeInRequestDTO.getShippingCountryISOCode());
        this.requestStatus = RequestStatus.PENDING;
//        this.products = products;
    }

    public void setShippingCountryFrom(String countryISOCode) {
        this.shippingCountry = CountryCode.getByCodeIgnoreCase(countryISOCode);
    }

    public void setRequestStatusFrom(String requestStatus) {
        this.requestStatus = RequestStatus.valueOf(requestStatus.replace(" ", "_").toUpperCase());
    }


    // -------------------- Hashcode and Equals --------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TradeInRequest that = (TradeInRequest) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(email, that.email) &&
                Objects.equals(mobileNumber, that.mobileNumber) &&
                Objects.equals(shippingCountry, that.shippingCountry) &&
                Objects.equals(requestStatus, that.requestStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, mobileNumber, shippingCountry, requestStatus);
    }
}
