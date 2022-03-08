package com.joaodss.tradeinwebsite.builder;

import com.google.api.services.sheets.v4.model.ValueRange;
import com.joaodss.tradeinwebsite.request.builder.ValueRangeBuilder;
import com.joaodss.tradeinwebsite.request.dto.BagDTO;
import com.joaodss.tradeinwebsite.request.dto.ResponseProductDTO;
import com.joaodss.tradeinwebsite.request.dto.ResponseTradeInRequestDTO;
import com.joaodss.tradeinwebsite.request.dto.ShoesDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(OrderAnnotation.class)
class ValueRangeBuilderTest {
    private ValueRangeBuilder valueRangeBuilder;
    private ResponseProductDTO responseProductDTO;
    private ResponseTradeInRequestDTO responseTradeInRequestDTO;


    @BeforeEach
    void setUp() {
        valueRangeBuilder = new ValueRangeBuilder();
        responseProductDTO = new ResponseProductDTO(
                1L,
                "PENDING",
                "BAG",
                "PRADA",
                "New Model",
                "USED",
                "Its ok",
                "link",
                new BagDTO(
                        "SMALL",
                        Set.of("Doe")
                ),
                null
        );
        responseTradeInRequestDTO = new ResponseTradeInRequestDTO(
                1L,
                "John",
                null,
                "random@email.com",
                "123456789",
                "USA",
                List.of(responseProductDTO)
        );
    }

    @Test
    @Order(1)
    void testAddTimeStamp_addNow() {
        LocalDateTime now = LocalDateTime.now();
        valueRangeBuilder.addTimeStamp();

        assertTrue(
                now
                        .isBefore(LocalDateTime.parse(valueRangeBuilder.getValueRange().getValues().get(0).get(0).toString()))
        );
        assertTrue(
                now.plus(3, ChronoUnit.SECONDS)
                        .isAfter(LocalDateTime.parse(valueRangeBuilder.getValueRange().getValues().get(0).get(0).toString()))
        );
    }


    @Test
    @Order(2)
    void testAddTradeInRequest_tradeInRequestWithMixedValues_addedToList() {
        valueRangeBuilder.addTradeInRequest(responseTradeInRequestDTO);

        assertEquals(
                List.of(1L, "John", "", "random@email.com", "123456789", "USA"),
                valueRangeBuilder.getValueRange().getValues().get(0)
        );
    }


    @Test
    @Order(3)
    void testAddProduct_bagProductWithMixedValues_addedToList() {
        valueRangeBuilder.addProduct(responseProductDTO);

        assertEquals(
                List.of(1L, "PENDING", "BAG", "PRADA", "New Model", "USED", "Its ok", "link", "SMALL", "[Doe]", ""),
                valueRangeBuilder.getValueRange().getValues().get(0)
        );
    }

    @Test
    @Order(4)
    void testAddProduct_shoesProductWithMixedValues_addedToList() {
        responseProductDTO.setBagDTO(null);
        responseProductDTO.setShoesDTO(new ShoesDTO((short) 23));
        valueRangeBuilder.addProduct(responseProductDTO);

        assertEquals(
                List.of(1L, "PENDING", "BAG", "PRADA", "New Model", "USED", "Its ok", "link", "", "", (short) 23),
                valueRangeBuilder.getValueRange().getValues().get(0)
        );
    }


    @Test
    @Order(4)
    void testAddValue_notNullObject_addItToValueRangeList() {
        valueRangeBuilder.addValue(123).addValue("TestTest").addValue(999L);
        assertEquals(List.of(123, "TestTest", 999L), valueRangeBuilder.getValueRange().getValues().get(0));
    }

    @Test
    @Order(4)
    void testAddValue_nullObject_addItToValueRangeList() {
        assertDoesNotThrow(() -> valueRangeBuilder.addValue(null).addValue(null));
        assertEquals(List.of("", ""), valueRangeBuilder.getValueRange().getValues().get(0));
    }


    @Test
    @Order(5)
    void testReset_listWithValues_emptiesList() {
        valueRangeBuilder.addValue(123);
        valueRangeBuilder.reset();

        assertEquals(List.of(), valueRangeBuilder.getValueRange().getValues().get(0));
    }

    @Test
    @Order(5)
    void testReset_initialProperties_doesNothing() {
        ValueRange initialValueRange = valueRangeBuilder.getValueRange();
        valueRangeBuilder.reset();

        assertEquals(initialValueRange, valueRangeBuilder.getValueRange());
    }


    @Test
    @Order(6)
    void testBuild_valueRangeCreated_returnValueRange() {
        assertEquals(valueRangeBuilder.getValueRange(), valueRangeBuilder.build());
    }

}
