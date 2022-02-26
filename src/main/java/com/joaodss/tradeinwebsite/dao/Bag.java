package com.joaodss.tradeinwebsite.dao;

import com.joaodss.tradeinwebsite.enums.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.EnumType.STRING;

@Entity
@Table(name = "bag")
@PrimaryKeyJoinColumn(name = "id")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Slf4j
public class Bag extends Product {

    @Enumerated(STRING)
    @Column(name = "bag_size", nullable = false)
    private BagSize bagSize;

    @Enumerated(STRING)
    @ElementCollection(targetClass = BagExtra.class)
    @CollectionTable(name = "bag_extra")
    @Column(name = "extra")
    private Set<BagExtra> bagExtras;

    @Column(name = "external_front_photo_URL")
    private String externalFrontURL;

    @Column(name = "external_back_photo_URL")
    private String externalBackURL;

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

    @Column(name = "internal_bottom_photo_URL")
    private String internalBottomURL;

    @Column(name = "internal_logo_photo_URL")
    private String internalLogoURL;

    @Column(name = "internal_serial_photo_URL")
    private String internalSerialURL;

    @Column(name = "internal_zipper_photo_URL")
    private String internalZipperURL;

    @Column(name = "internal_hardware_photo_URL")
    private String internalHardwareURL;


    // -------------------- Custom Constructor --------------------
    public Bag(
            Category category,
            Brand brand,
            String model,
            Condition condition,
            String details,
            List<String> blemishPhotos,
            BagSize bagSize,
            Set<BagExtra> bagExtras
    ) {
        super(category, brand, model, condition, details, blemishPhotos);
        this.bagSize = bagSize;
        this.bagExtras = bagExtras;
    }


    // -------------------- Custom Methods --------------------
    public void setBagSizeFrom(String size) {
        this.bagSize = BagSize.valueOf(size.replace(" ", "_").toUpperCase());
    }

    public void setBagExtrasFrom(Set<String> extras) {
        Set<BagExtra> bagExtras = new HashSet<>();
        for (String extra : extras) {
            bagExtras.add(BagExtra.valueOf(extra.replace(" ", "_").toUpperCase()));
        }
        this.bagExtras = bagExtras;
    }


    // -------------------- Hashcode and Equals --------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Bag bag = (Bag) o;
        return Objects.equals(bagSize, bag.bagSize) &&
                Objects.equals(bagExtras, bag.bagExtras) &&
                Objects.equals(externalFrontURL, bag.externalFrontURL) &&
                Objects.equals(externalBackURL, bag.externalBackURL) &&
                Objects.equals(externalBottomURL, bag.externalBottomURL) &&
                Objects.equals(externalLogoURL, bag.externalLogoURL) &&
                Objects.equals(externalSerialURL, bag.externalSerialURL) &&
                Objects.equals(externalZipperURL, bag.externalZipperURL) &&
                Objects.equals(externalHardwareURL, bag.externalHardwareURL) &&
                Objects.equals(internalInsideURL, bag.internalInsideURL) &&
                Objects.equals(internalBottomURL, bag.internalBottomURL) &&
                Objects.equals(internalLogoURL, bag.internalLogoURL) &&
                Objects.equals(internalSerialURL, bag.internalSerialURL) &&
                Objects.equals(internalZipperURL, bag.internalZipperURL) &&
                Objects.equals(internalHardwareURL, bag.internalHardwareURL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                super.hashCode(), bagSize, bagExtras, externalFrontURL, externalBackURL, externalBottomURL,
                externalLogoURL, externalSerialURL, externalZipperURL, externalHardwareURL, internalInsideURL,
                internalBottomURL, internalLogoURL, internalSerialURL, internalZipperURL, internalHardwareURL
        );
    }
}
