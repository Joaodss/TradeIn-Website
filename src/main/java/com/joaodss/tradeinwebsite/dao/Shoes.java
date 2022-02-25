package com.joaodss.tradeinwebsite.dao;

import com.joaodss.tradeinwebsite.datatype.ShoesPhotos;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
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

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "externalTopURL", column = @Column(name = "external_top_photo_URL")),
            @AttributeOverride(name = "externalLeftSideURL", column = @Column(name = "external_left_photo_URL")),
            @AttributeOverride(name = "externalRightSideURL", column = @Column(name = "external_right_photo_URL")),
            @AttributeOverride(name = "externalBottomURL", column = @Column(name = "external_bottom_photo_URL")),
            @AttributeOverride(name = "externalLogoURL", column = @Column(name = "external_logo_photo_URL")),
            @AttributeOverride(name = "externalSerialURL", column = @Column(name = "external_serial_photo_URL")),
            @AttributeOverride(name = "externalZipperURL", column = @Column(name = "external_zipper_photo_URL")),
            @AttributeOverride(name = "externalHardwareURL", column = @Column(name = "external_hardware_photo_URL")),
            @AttributeOverride(name = "internalInsideURL", column = @Column(name = "internal_inside_photo_URL")),
            @AttributeOverride(name = "internalLogoURL", column = @Column(name = "internal_logo_photo_URL")),
            @AttributeOverride(name = "internalSerialURL", column = @Column(name = "internal_serial_photo_URL"))
    })
    private ShoesPhotos shoesPhotos;


    // -------------------- Custom Constructor --------------------


    // -------------------- Hashcode and Equals --------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Shoes shoes = (Shoes) o;
        return shoesSize.equals(shoes.shoesSize) &&
                Objects.equals(shoesPhotos, shoes.shoesPhotos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), shoesSize, shoesPhotos);
    }
}
