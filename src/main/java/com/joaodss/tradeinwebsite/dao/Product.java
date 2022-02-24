package com.joaodss.tradeinwebsite.dao;

import com.joaodss.tradeinwebsite.enums.Brand;
import com.joaodss.tradeinwebsite.enums.Category;
import com.joaodss.tradeinwebsite.enums.Condition;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Slf4j
public abstract class Product {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trade_in_request_id")
    private TradeInRequest tradeInRequest;

    @Enumerated(STRING)
    @Column(name = "category", nullable = false)
    private Category category;

    @Enumerated(STRING)
    @Column(name = "brand", nullable = false)
    private Brand brand;

    @Column(name = "model", nullable = false)
    private String model;

    @Enumerated(STRING)
    @Column(name = "condition", nullable = false)
    private Condition condition;

    @Column(name = "details")
    private String details;

    @ToString.Exclude
    @OneToMany(mappedBy = "photoURL")
    private List<BlemishPhoto> blemishPhotos = new ArrayList<>();


    // -------------------- Custom Constructor --------------------


    // -------------------- Hashcode and Equals --------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id) &&
                tradeInRequest.equals(product.tradeInRequest) &&
                category == product.category &&
                brand == product.brand &&
                model.equals(product.model) &&
                condition == product.condition &&
                Objects.equals(details, product.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tradeInRequest, category, brand, model, condition, details);
    }
}
