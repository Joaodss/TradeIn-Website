package com.joaodss.tradeinwebsite.dto.specification;

import com.joaodss.tradeinwebsite.dao.specification.Brand;
import com.joaodss.tradeinwebsite.dao.specification.Category;
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
