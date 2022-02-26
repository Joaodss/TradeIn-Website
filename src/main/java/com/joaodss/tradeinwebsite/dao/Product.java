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

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.InheritanceType.JOINED;

@Entity
@Inheritance(strategy = JOINED)
@Table(name = "product")
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
    @JoinColumn(name = "trade_in_request_id", nullable = false)
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
    @Column(name = "product_condition", nullable = false)
    private Condition condition;

    @Column(name = "details")
    private String details;

    @ElementCollection(targetClass = String.class, fetch = EAGER)
    @CollectionTable(name = "blemish_photos")
    @Column(name = "photo_url")
    private List<String> blemishPhotos = new ArrayList<>();


    // -------------------- Custom Constructor --------------------
    public Product(
            Category category,
            Brand brand,
            String model,
            Condition condition,
            String details,
            List<String> blemishPhotos
    ) {
        this.category = category;
        this.brand = brand;
        this.model = model;
        this.condition = condition;
        this.details = details;
        this.blemishPhotos = blemishPhotos;
    }


    // -------------------- Custom Methods --------------------
    public void setCategoryFrom(String category) {
        this.category = Category.valueOf(category.replace(" ", "_").toUpperCase());
    }

    public void setBrandFrom(String brand) {
        this.brand = Brand.valueOf(brand.replace(" ", "_").toUpperCase());
    }

    public void setConditionFrom(String condition) {
        this.condition = Condition.valueOf(condition.replace(" ", "_").toUpperCase());
    }

    // -------------------- Hashcode and Equals --------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(tradeInRequest, product.tradeInRequest) &&
                Objects.equals(category, product.category) &&
                Objects.equals(brand, product.brand) &&
                Objects.equals(model, product.model) &&
                Objects.equals(condition, product.condition) &&
                Objects.equals(details, product.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tradeInRequest, category, brand, model, condition, details);
    }
}
