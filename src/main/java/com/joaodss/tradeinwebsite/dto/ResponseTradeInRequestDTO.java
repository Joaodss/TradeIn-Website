package com.joaodss.tradeinwebsite.dto;

import com.joaodss.tradeinwebsite.dao.Product;
import com.joaodss.tradeinwebsite.dao.TradeInRequest;
import com.joaodss.tradeinwebsite.enums.RequestStatus;
import com.neovisionaries.i18n.CountryCode;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private String shippingCountry;
    private String requestStatus;
    private List<ResponseProductDTO> products;


    // -------------------- Custom Constructor --------------------
    public ResponseTradeInRequestDTO(TradeInRequest tradeInRequest) {
        this.id = tradeInRequest.getId();
        this.firstName = tradeInRequest.getFirstName();
        this.lastName = tradeInRequest.getLastName();
        this.email = tradeInRequest.getEmail();
        this.mobileNumber = tradeInRequest.getMobileNumber();
        setShippingCountry(tradeInRequest.getShippingCountry());
        setRequestStatus(tradeInRequest.getRequestStatus());
        setProducts(tradeInRequest.getProducts());
    }


    // -------------------- Custom Methods --------------------
    private void setShippingCountry(CountryCode countryCode) {
        this.shippingCountry = countryCode.toString();
    }

    private void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus.toString();
    }

    private void setProducts(List<Product> productList) {
        this.products = new ArrayList<>();
        for (Product product : productList)
            this.products.add(new ResponseProductDTO(product));
    }

}
