package com.joaodss.tradeinwebsite.dao;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "shoes")
@PrimaryKeyJoinColumn(name = "id")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Slf4j
public class Shoes extends Product {

    @Column(name = "shoes_size", nullable = false)
    private Short shoesSize;


    // -------------------- Custom Constructor --------------------


    // -------------------- Hashcode and Equals --------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Shoes shoes = (Shoes) o;
        return shoesSize.equals(shoes.shoesSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), shoesSize);
    }
}
