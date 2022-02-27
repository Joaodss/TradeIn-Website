package com.joaodss.tradeinwebsite.dto;

import com.joaodss.tradeinwebsite.dao.TradeInRequest;
import com.joaodss.tradeinwebsite.enums.Brand;
import com.joaodss.tradeinwebsite.enums.Category;
import com.joaodss.tradeinwebsite.enums.Condition;
import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ResponseProductDTO {
    private Long id;
    private TradeInRequest tradeInRequest;
    private Category category;
    private Brand brand;
    private String model;
    private Condition condition;
    private String details;
    private List<String> blemishPhotos;

}
