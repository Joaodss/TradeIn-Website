package com.joaodss.tradeinwebsite.dao;

import com.joaodss.tradeinwebsite.datatype.Contact;
import com.joaodss.tradeinwebsite.enums.RequestStatus;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static javax.persistence.CascadeType.REMOVE;
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

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "first_name")),
            @AttributeOverride(name = "lastName", column = @Column(name = "last_name")),
            @AttributeOverride(name = "email", column = @Column(name = "email", nullable = false)),
            @AttributeOverride(name = "mobileNumber", column = @Column(name = "mobile_number"))
    })
    private Contact contact;

    @Column(name = "shipping_country", nullable = false)
    private Locale shippingCountry;

    @Enumerated(STRING)
    @Column(name = "request_status", nullable = false)
    private RequestStatus requestStatus;

    @ToString.Exclude
    @OneToMany(mappedBy = "tradeInRequest", cascade = {REMOVE})
    private List<Product> products = new ArrayList<>();


    // -------------------- Hashcode and Equals --------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TradeInRequest that = (TradeInRequest) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(contact, that.contact) &&
                Objects.equals(shippingCountry, that.shippingCountry) &&
                requestStatus == that.requestStatus &&
                Objects.equals(products, that.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contact, shippingCountry, requestStatus, products);
    }
}
