package com.joaodss.tradeinwebsite.builder;

import com.google.api.services.sheets.v4.model.ValueRange;
import com.joaodss.tradeinwebsite.dto.ResponseProductDTO;
import com.joaodss.tradeinwebsite.dto.ResponseTradeInRequestDTO;

import java.util.LinkedList;
import java.util.List;

public class ValueRangeBuilder {
    private ValueRange valueRange;


    public ValueRangeBuilder() {
        this.valueRange = new ValueRange();
        this.valueRange
                .setMajorDimension("ROWS")
                .setValues(List.of(new LinkedList<>()));
    }


    public ValueRangeBuilder addTradeInRequest(ResponseTradeInRequestDTO tradeInRequest) {
        valueRange.getValues().get(0).add(tradeInRequest.getId());
        valueRange.getValues().get(0).add(tradeInRequest.getFirstName());
        valueRange.getValues().get(0).add(tradeInRequest.getLastName());
        valueRange.getValues().get(0).add(tradeInRequest.getEmail());
        valueRange.getValues().get(0).add(tradeInRequest.getMobileNumber());
        valueRange.getValues().get(0).add(tradeInRequest.getShippingCountry());
        return this;
    }

    public ValueRangeBuilder addProduct(ResponseProductDTO product) {
        valueRange.getValues().get(0).add(product.getId());
        valueRange.getValues().get(0).add(product.getRequestStatus());
        valueRange.getValues().get(0).add(product.getCategory());
        valueRange.getValues().get(0).add(product.getBrand());
        valueRange.getValues().get(0).add(product.getModel());
        valueRange.getValues().get(0).add(product.getCondition());
        valueRange.getValues().get(0).add(product.getDetails());
        valueRange.getValues().get(0).add(product.getPhotosFolderURL());
        valueRange.getValues().get(0).add(
                product.getBagDTO() != null ? product.getBagDTO().getSize() : ""
        );
        valueRange.getValues().get(0).add(
                product.getBagDTO() != null ? product.getBagDTO().getExtras().toString() : ""
        );
        valueRange.getValues().get(0).add(
                product.getShoesDTO() != null ? product.getShoesDTO().getSize() : ""
        );
        return this;
    }

    public ValueRangeBuilder reset() {
        valueRange = new ValueRange();
        return this;
    }

    public ValueRange build() {
        return this.valueRange;
    }

}
