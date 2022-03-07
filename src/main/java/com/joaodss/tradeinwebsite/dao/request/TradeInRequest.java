package com.joaodss.tradeinwebsite.dao.request;

import com.joaodss.tradeinwebsite.builder.ProductBuilder;
import com.joaodss.tradeinwebsite.dto.request.ProductDTO;
import com.joaodss.tradeinwebsite.dto.request.TradeInRequestDTO;
import com.neovisionaries.i18n.CountryCode;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static javax.persistence.CascadeType.*;
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

    @ToString.Exclude
    @OneToMany(mappedBy = "tradeInRequest", cascade = {PERSIST, MERGE, REMOVE}, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();


    // -------------------- Custom Constructor --------------------
    public TradeInRequest(
            String firstName,
            String lastName,
            String email,
            String mobileNumber,
            CountryCode shippingCountry
    ) {
        log.info("Constructing TradeInRequest manually");
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.shippingCountry = shippingCountry;
    }

    public TradeInRequest(TradeInRequestDTO tradeInRequestDTO) {
        log.info("Constructing TradeInRequest from DTO");
        this.firstName = tradeInRequestDTO.getFirstName();
        this.lastName = tradeInRequestDTO.getLastName();
        this.email = tradeInRequestDTO.getEmail();
        this.mobileNumber = tradeInRequestDTO.getMobileNumber();
        setShippingCountryFrom(tradeInRequestDTO.getShippingCountryISOCode());
        setNewProducts(tradeInRequestDTO.getProducts());
    }


    // -------------------- Custom Methods --------------------
    public void setShippingCountryFrom(String countryISOCode) {
        this.shippingCountry = CountryCode.getByCodeIgnoreCase(countryISOCode);
        if (this.shippingCountry == null)
            throw new IllegalArgumentException("No country ISO code for: " + countryISOCode + ".");
    }

    public void setNewProducts(List<ProductDTO> productDTOList) {
        ProductBuilder productBuilder = new ProductBuilder();
        resetProducts();
        for (ProductDTO productDTO : productDTOList) {
            Product newProduct = productBuilder
                    .buildProductFrom(productDTO)
                    .build();
            addProduct(newProduct);
        }
    }

    public void resetProducts() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        product.setTradeInRequest(this);
        this.products.add(product);
    }

    public void updateProduct(Product product) {
        product.setTradeInRequest(this);
        for (int i = 0; i < this.products.size(); i++) {
            if (Objects.equals(this.products.get(i).getId(), product.getId())
                    || Objects.equals(this.products.get(i), product))
                this.products.set(i, product);
        }
    }

    public void removeProduct(Product product) {
        if (this.products.size() <= 1)
            throw new IllegalStateException("TradeInRequest must have at least 1 Product element");
        else
            this.products.remove(product);
    }


    // -------------------- Hashcode and Equals --------------------
    @Generated
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
                Objects.equals(shippingCountry, that.shippingCountry);
    }

    @Generated
    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, mobileNumber, shippingCountry);
    }

}
