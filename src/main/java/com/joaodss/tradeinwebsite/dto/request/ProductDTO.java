package com.joaodss.tradeinwebsite.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ProductDTO {

    @NotNull(message = "Category is required.")
    @NotBlank(message = "Category is required.")
    private String category;

    @NotNull(message = "Brand is required.")
    @NotBlank(message = "Brand is required.")
    private String brand;

    @NotNull(message = "Model is required.")
    @NotBlank(message = "Model is required.")
    private String model;

    @NotNull(message = "Condition is required.")
    @NotBlank(message = "Condition is required.")
    private String condition;

    @NotNull(message = "Description cannot be null.")
    private String details;

    @NotNull(message = "Photo Folder URL is required.")
    @NotBlank(message = "Photo Folder URL is required.")
    private String photosFolderURL;

    private BagDTO bagDTO;

    private ShoesDTO shoesDTO;

}
