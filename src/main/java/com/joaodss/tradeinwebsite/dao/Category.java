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
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Slf4j
public class Category {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String categoryName;

    @OneToMany(mappedBy = "category", cascade = {}, orphanRemoval = true, fetch = LAZY)
    @ToString.Exclude
    private Set<CategoryBrandSpecification> specification = new HashSet<>();


    // -------------------- Custom Constructor --------------------
    public Category(String categoryName, Set<CategoryBrandSpecification> specification) {
        this.categoryName = categoryName;
        this.specification = specification;
    }

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }


    // -------------------- Hashcode and Equals --------------------
    @Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) &&
                Objects.equals(categoryName, category.categoryName);
    }

    @Generated
    @Override
    public int hashCode() {
        return Objects.hash(id, categoryName);
    }

}
