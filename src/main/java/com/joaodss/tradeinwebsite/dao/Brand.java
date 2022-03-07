package com.joaodss.tradeinwebsite.dao;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "brand")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Slf4j
public class Brand {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String brandName;

    @OneToMany(mappedBy = "brand", cascade = {}, orphanRemoval = true, fetch = LAZY)
    @ToString.Exclude
    private Set<CategoryBrandSpecification> specification = new HashSet<>();


    // -------------------- Custom Constructor --------------------
    public Brand(String brandName, Set<CategoryBrandSpecification> specification) {
        this.brandName = brandName;
        this.specification = specification;
    }

    public Brand(String brandName) {
        this.brandName = brandName;
    }


    // -------------------- Hashcode and Equals --------------------
    @Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brand brand = (Brand) o;
        return Objects.equals(id, brand.id) &&
                Objects.equals(brandName, brand.brandName);
    }

    @Generated
    @Override
    public int hashCode() {
        return Objects.hash(id, brandName);
    }

}
