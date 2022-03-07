package com.joaodss.tradeinwebsite.dao;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "category_brand_specification")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Slf4j
public class CategoryBrandSpecification {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ManyToOne(cascade = {PERSIST}, optional = false, fetch = EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(cascade = {PERSIST}, optional = false, fetch = EAGER)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ElementCollection(targetClass = String.class, fetch = EAGER)
    @CollectionTable(name = "mandatory_external_photo_tags")
    @Column(name = "tag")
    private Set<String> mandatoryExternalPhotos;

    @ElementCollection(targetClass = String.class, fetch = EAGER)
    @CollectionTable(name = "external_photo_tags")
    @Column(name = "tag")
    private Set<String> externalPhotos;

    @ElementCollection(targetClass = String.class, fetch = EAGER)
    @CollectionTable(name = "mandatory_internal_photo_tags")
    @Column(name = "tag")
    private Set<String> mandatoryInternalPhotos;

    @ElementCollection(targetClass = String.class, fetch = EAGER)
    @CollectionTable(name = "internal_photo_tags")
    @Column(name = "tag")
    private Set<String> internalPhotos;


    // -------------------- Custom Constructor --------------------
    public CategoryBrandSpecification(
            Category category,
            Brand brand,
            Set<String> mandatoryExternalPhotos,
            Set<String> externalPhotos,
            Set<String> mandatoryInternalPhotos,
            Set<String> internalPhotos
    ) {
        this.category = category;
        this.brand = brand;
        this.mandatoryExternalPhotos = mandatoryExternalPhotos;
        this.externalPhotos = externalPhotos;
        this.mandatoryInternalPhotos = mandatoryInternalPhotos;
        this.internalPhotos = internalPhotos;
    }


    // -------------------- Custom Methods --------------------

    // TODO: Delete if not needed

//    public void addTagToSet(String tag, String setName) {
//        log.info("Adding tag {} to set {}", tag, setName);
//        switch (setName.trim().toLowerCase()) {
//            case "mandatory_external" -> addTagToMandatoryExternalSet(tag);
//            case "external" -> addTagToExternalSet(tag);
//            case "mandatory_internal" -> addTagToMandatoryInternalSet(tag);
//            case "internal" -> addTagToInternalSet(tag);
//            default -> {
//                log.error("Invalid set name {}", setName);
//                throw new IllegalArgumentException("Invalid set name");
//            }
//        }
//    }
//
//    private void addTagToMandatoryExternalSet(String tag) {
//        this.mandatoryExternalPhotos.add(tag);
//    }
//
//    private void addTagToExternalSet(String tag) {
//        this.externalPhotos.add(tag);
//    }
//
//    private void addTagToMandatoryInternalSet(String tag) {
//        this.mandatoryInternalPhotos.add(tag);
//    }
//
//    private void addTagToInternalSet(String tag) {
//        this.internalPhotos.add(tag);
//    }


    // -------------------- Hashcode and Equals --------------------
    @Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryBrandSpecification that = (CategoryBrandSpecification) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(category, that.category) &&
                Objects.equals(brand, that.brand);
    }

    @Generated
    @Override
    public int hashCode() {
        return Objects.hash(id, category, brand);
    }

}
