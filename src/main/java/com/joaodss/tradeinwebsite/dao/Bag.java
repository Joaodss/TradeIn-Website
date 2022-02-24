package com.joaodss.tradeinwebsite.dao;

import com.joaodss.tradeinwebsite.enums.BagExtra;
import com.joaodss.tradeinwebsite.enums.BagSize;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
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

}
