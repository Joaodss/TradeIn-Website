package com.joaodss.tradeinwebsite.dao;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Blemish_photo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Slf4j
public class BlemishPhoto {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "photo_url")
    private String photoURL;


    // -------------------- Custom Constructor --------------------
    public BlemishPhoto(Product product, String photoURL) {
        this.product = product;
        this.photoURL = photoURL;
    }


    // -------------------- Hashcode and Equals --------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlemishPhoto that = (BlemishPhoto) o;
        return Objects.equals(id, that.id) && Objects.equals(product, that.product) && Objects.equals(photoURL, that.photoURL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, photoURL);
    }
}
