package com.joaodss.tradeinwebsite.dao;

import com.joaodss.tradeinwebsite.dto.ProductDTO;
import com.joaodss.tradeinwebsite.enums.Brand;
import com.joaodss.tradeinwebsite.enums.Category;
import com.joaodss.tradeinwebsite.enums.Condition;
import com.joaodss.tradeinwebsite.enums.RequestStatus;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Objects;

import static com.joaodss.tradeinwebsite.enums.RequestStatus.PENDING;
import static com.joaodss.tradeinwebsite.enums.RequestStatus.valueOf;
import static com.joaodss.tradeinwebsite.utils.EnumsUtil.enumFormat;
import static javax.persistence.EnumType.STRING;
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
    @Column(name = "request_status", nullable = false)
    private RequestStatus requestStatus;

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

    @Column(name = "photos_folder_url", nullable = false)
    private String photosFolderURL;


    // -------------------- Custom Constructor --------------------
    public Product(
            RequestStatus requestStatus,
            Category category,
            Brand brand,
            String model,
            Condition condition,
            String details,
            String photosFolderURL
    ) {
        log.info("Constructing Product manually");
        this.requestStatus = requestStatus;
        this.category = category;
        this.brand = brand;
        this.model = model;
        this.condition = condition;
        this.details = details;
        this.photosFolderURL = photosFolderURL;
    }

    public Product(ProductDTO productDTO) {
        log.info("Constructing Product from DTO");
        this.requestStatus = PENDING;
        setCategoryFrom(productDTO.getCategory());
        setBrandFrom(productDTO.getBrand());
        this.model = productDTO.getModel();
        setConditionFrom(productDTO.getCondition());
        this.details = productDTO.getDetails();
        this.photosFolderURL = productDTO.getPhotosFolderURL();
    }


    // -------------------- Custom Methods --------------------
    public void setRequestStatusFrom(String requestStatus) {
        this.requestStatus = valueOf(enumFormat(requestStatus));
    }

    public void setCategoryFrom(String category) {
        this.category = Category.valueOf(enumFormat(category));
    }

    public void setBrandFrom(String brand) {
        this.brand = Brand.valueOf(enumFormat(brand));
    }

    public void setConditionFrom(String condition) {
        this.condition = Condition.valueOf(enumFormat(condition));
    }


    // -------------------- Hashcode and Equals --------------------
    @Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(requestStatus, product.requestStatus) &&
                Objects.equals(category, product.category) &&
                Objects.equals(brand, product.brand) &&
                Objects.equals(model, product.model) &&
                Objects.equals(condition, product.condition) &&
                Objects.equals(details, product.details) &&
                Objects.equals(photosFolderURL, product.photosFolderURL);
    }

    @Generated
    @Override
    public int hashCode() {
        return Objects.hash(id, requestStatus, category, brand, model, condition, details, photosFolderURL);
    }

}
