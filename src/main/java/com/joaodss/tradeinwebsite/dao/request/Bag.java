package com.joaodss.tradeinwebsite.dao.request;

import com.joaodss.tradeinwebsite.dao.specification.Brand;
import com.joaodss.tradeinwebsite.dao.specification.Category;
import com.joaodss.tradeinwebsite.dto.request.ProductDTO;
import com.joaodss.tradeinwebsite.enums.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static com.joaodss.tradeinwebsite.utils.EnumsUtil.enumFormat;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.EAGER;

@Entity
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
    @ElementCollection(targetClass = BagExtra.class, fetch = EAGER)
    @CollectionTable(name = "bag_extra")
    @Column(name = "extra")
    private Set<BagExtra> bagExtras;


    // -------------------- Custom Constructor --------------------
    public Bag(
            RequestStatus requestStatus,
            Category category,
            Brand brand,
            String model,
            Condition condition,
            String details,
            String photosFolderURL,
            BagSize bagSize,
            Set<BagExtra> bagExtras
    ) {
        super(requestStatus, category, brand, model, condition, details, photosFolderURL);
        log.info("Constructing Bag manually");
        this.bagSize = bagSize;
        this.bagExtras = bagExtras;
    }

    public Bag(ProductDTO productDTO) {
        super(productDTO);
        log.info("Constructing Bag from DTO");
        setBagSizeFrom(productDTO.getBagDTO().getSize());
        setBagExtrasFrom(productDTO.getBagDTO().getExtras());
    }


    // -------------------- Custom Methods --------------------
    public void setBagSizeFrom(String size) {
        this.bagSize = BagSize.valueOf(enumFormat(size));
    }

    public void setBagExtrasFrom(Set<String> extras) {
        initializeExtrasIfNull();
        for (String extra : extras)
            addExtraFrom(extra);
    }

    public void addExtraFrom(String extra) {
        initializeExtrasIfNull();
        this.bagExtras.add(BagExtra.valueOf(enumFormat(extra)));
    }

    public void initializeExtrasIfNull() {
        if (this.bagExtras == null)
            resetExtras();
    }

    public void resetExtras() {
        this.bagExtras = new HashSet<>();
    }


    // -------------------- Hashcode and Equals --------------------
    @Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Bag bag = (Bag) o;
        return Objects.equals(bagSize, bag.bagSize) &&
                Objects.equals(bagExtras, bag.bagExtras);
    }

    @Generated
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bagSize, bagExtras);
    }

}
