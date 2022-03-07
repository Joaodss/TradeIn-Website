package com.joaodss.tradeinwebsite.builder;

import com.google.api.services.sheets.v4.model.ValueRange;
import com.joaodss.tradeinwebsite.dto.request.ResponseProductDTO;
import com.joaodss.tradeinwebsite.dto.request.ResponseTradeInRequestDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class ValueRangeBuilder {
    private ValueRange valueRange;


    public ValueRangeBuilder() {
        reset();
    }

    public ValueRangeBuilder addTimeStamp() {
        addValue(LocalDateTime.now().toString());
        return this;
    }

    public ValueRangeBuilder addTradeInRequest(ResponseTradeInRequestDTO tradeInRequest) {
        addValue(tradeInRequest.getId());
        addValue(tradeInRequest.getFirstName());
        addValue(tradeInRequest.getLastName());
        addValue(tradeInRequest.getEmail());
        addValue(tradeInRequest.getMobileNumber());
        addValue(tradeInRequest.getShippingCountry());
        return this;
    }

    public ValueRangeBuilder addProduct(ResponseProductDTO product) {
        addValue(product.getId());
        addValue(product.getRequestStatus());
        addValue(product.getCategory());
        addValue(product.getBrand());
        addValue(product.getModel());
        addValue(product.getCondition());
        addValue(product.getDetails());
        addValue(product.getPhotosFolderURL());
        addValue(
                product.getBagDTO() != null ? product.getBagDTO().getSize() : ""
        );
        addValue(
                product.getBagDTO() != null ? product.getBagDTO().getExtras().toString() : ""
        );
        addValue(
                product.getShoesDTO() != null ? product.getShoesDTO().getSize() : ""
        );
        return this;
    }

    public ValueRangeBuilder addValue(Object value) {
        this.valueRange.getValues().get(0).add(
                value != null ? value : ""
        );
        return this;
    }

    public ValueRangeBuilder reset() {
        this.valueRange = new ValueRange();
        this.valueRange
                .setMajorDimension("ROWS")
                .setValues(List.of(new LinkedList<>()));
        return this;
    }

    public ValueRange build() {
        return this.valueRange;
    }

}
