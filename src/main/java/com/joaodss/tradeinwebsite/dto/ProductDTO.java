package com.joaodss.tradeinwebsite.dto;

import lombok.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ProductDTO {
    private String category;
    private String brand;
    private String model;
    private String condition;
    private String details;
    private BagDTO bagDTO;
    private ShoesDTO shoesDTO;
    private List<String> blemishPhotos;
}
