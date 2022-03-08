package com.joaodss.tradeinwebsite.categorybrandspecification.dto;

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
public class NewCategoryBrandSpecificationDTO {

    @NotNull
    @NotBlank
    private String category;

    @NotNull
    @NotBlank
    private String brand;

    private List<String> mandatoryExternalPhotos;

    private List<String> externalPhotos;

    private List<String> mandatoryInternalPhotos;

    private List<String> internalPhotos;

}
