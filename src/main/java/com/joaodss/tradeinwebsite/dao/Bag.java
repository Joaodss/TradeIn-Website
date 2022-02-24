package com.joaodss.tradeinwebsite.dao;

import com.joaodss.tradeinwebsite.datatype.BagPhotos;
import com.joaodss.tradeinwebsite.enums.BagExtra;
import com.joaodss.tradeinwebsite.enums.BagSize;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
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

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "externalFrontURL", column = @Column(name = "external_front_photo_URL")),
            @AttributeOverride(name = "externalBackURL", column = @Column(name = "external_back_photo_URL")),
            @AttributeOverride(name = "externalBottomURL", column = @Column(name = "external_bottom_photo_URL")),
            @AttributeOverride(name = "externalLogoURL", column = @Column(name = "external_logo_photo_URL")),
            @AttributeOverride(name = "externalSerialURL", column = @Column(name = "external_serial_photo_URL")),
            @AttributeOverride(name = "externalZipperURL", column = @Column(name = "external_zipper_photo_URL")),
            @AttributeOverride(name = "externalHardwareURL", column = @Column(name = "external_hardware_photo_URL")),
            @AttributeOverride(name = "internalInsideURL", column = @Column(name = "internal_inside_photo_URL")),
            @AttributeOverride(name = "internalBottomURL", column = @Column(name = "internal_bottom_photo_URL")),
            @AttributeOverride(name = "internalLogoURL", column = @Column(name = "internal_logo_photo_URL")),
            @AttributeOverride(name = "internalSerialURL", column = @Column(name = "internal_serial_photo_URL")),
            @AttributeOverride(name = "internalZipperURL", column = @Column(name = "internal_zipper_photo_URL")),
            @AttributeOverride(name = "internalHardwareURL", column = @Column(name = "internal_hardware_photo_URL")),
    })
    private BagPhotos bagPhotos;


    // -------------------- Custom Constructor --------------------


    // -------------------- Hashcode and Equals --------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Bag bag = (Bag) o;
        return bagSize == bag.bagSize &&
                Objects.equals(bagExtras, bag.bagExtras) &&
                Objects.equals(bagPhotos, bag.bagPhotos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bagSize, bagExtras, bagPhotos);
    }
}
