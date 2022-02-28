package com.joaodss.tradeinwebsite.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ProductDTO {

    @NotNull
    @NotBlank
    private String category;

    @NotNull
    @NotBlank
    private String brand;

    @NotNull
    @NotBlank
    private String model;

    @NotNull
    @NotBlank
    private String condition;

    @NotNull
    private String details;

    private BagDTO bagDTO;

    private ShoesDTO shoesDTO;

    private List<String> blemishPhotos;

}
