package com.joaodss.tradeinwebsite.dao;

import com.joaodss.tradeinwebsite.enums.Brand;
import com.joaodss.tradeinwebsite.enums.Category;
import com.joaodss.tradeinwebsite.enums.Condition;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.List;
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

    @Column(name = "external_top_photo_URL")
    private String externalTopURL;

    @Column(name = "external_left_photo_URL")
    private String externalLeftSideURL;

    @Column(name = "external_right_photo_URL")
    private String externalRightSideURL;

    @Column(name = "external_bottom_photo_URL")
    private String externalBottomURL;

    @Column(name = "external_logo_photo_URL")
    private String externalLogoURL;

    @Column(name = "external_serial_photo_URL")
    private String externalSerialURL;

    @Column(name = "external_zipper_photo_URL")
    private String externalZipperURL;

    @Column(name = "external_hardware_photo_URL")
    private String externalHardwareURL;

    @Column(name = "internal_inside_photo_URL")
    private String internalInsideURL;

    @Column(name = "internal_logo_photo_URL")
    private String internalLogoURL;

    @Column(name = "internal_serial_photo_URL")
    private String internalSerialURL;


    // -------------------- Custom Constructor --------------------
    public Shoes(
            Category category,
            Brand brand,
            String model,
            Condition condition,
            String details,
            List<String> blemishPhotos,
            Short shoesSize
    ) {
        super(category, brand, model, condition, details, blemishPhotos);
        this.shoesSize = shoesSize;
    }


    // -------------------- Hashcode and Equals --------------------
    @Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Shoes shoes = (Shoes) o;
        return Objects.equals(shoesSize, shoes.shoesSize) &&
                Objects.equals(externalTopURL, shoes.externalTopURL) &&
                Objects.equals(externalLeftSideURL, shoes.externalLeftSideURL) &&
                Objects.equals(externalRightSideURL, shoes.externalRightSideURL) &&
                Objects.equals(externalBottomURL, shoes.externalBottomURL) &&
                Objects.equals(externalLogoURL, shoes.externalLogoURL) &&
                Objects.equals(externalSerialURL, shoes.externalSerialURL) &&
                Objects.equals(externalZipperURL, shoes.externalZipperURL) &&
                Objects.equals(externalHardwareURL, shoes.externalHardwareURL) &&
                Objects.equals(internalInsideURL, shoes.internalInsideURL) &&
                Objects.equals(internalLogoURL, shoes.internalLogoURL) &&
                Objects.equals(internalSerialURL, shoes.internalSerialURL);
    }

    @Generated
    @Override
    public int hashCode() {
        return Objects.hash(
                super.hashCode(), shoesSize, externalTopURL, externalLeftSideURL, externalRightSideURL,
                externalBottomURL, externalLogoURL, externalSerialURL, externalZipperURL, externalHardwareURL,
                internalInsideURL, internalLogoURL, internalSerialURL
        );
    }
}
