package com.joaodss.tradeinwebsite.categorybrandspecification.dto;

import com.joaodss.tradeinwebsite.categorybrandspecification.dao.Category;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CategoryDTO {
    private Long id;
    private String name;


    // -------------------- Custom Constructor --------------------
    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getCategoryName();
    }

}
