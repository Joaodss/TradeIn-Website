package com.joaodss.tradeinwebsite.builder.builders;

import com.joaodss.tradeinwebsite.dao.Shoes;
import com.joaodss.tradeinwebsite.dto.ProductDTO;
import com.joaodss.tradeinwebsite.dto.ShoesDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.joaodss.tradeinwebsite.enums.Brand.GUCCI;
import static com.joaodss.tradeinwebsite.enums.Category.SHOES;
import static com.joaodss.tradeinwebsite.enums.Condition.VERY_GOOD;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class ShoesBuilderTest {

    @InjectMocks
    @Spy
    private ShoesBuilder shoesBuilder;

    ProductDTO productDTO = new ProductDTO(
            "Shoes",
            "Gucci",
            "Horsebit mid-heel slingback pumps",
            "Very Good",
            "In very good shape",
            null,
            new ShoesDTO((short) 12),
            List.of()
    );

    @Test
    @Order(1)
    void testBuildFrom_productDTO_usesInternMethods() {
        shoesBuilder.buildFrom(productDTO);
        verify(shoesBuilder).buildFrom(productDTO);

        assertEquals(productDTO, shoesBuilder.getProductDTO());
        verify(shoesBuilder).getProductDTO();

        verify(shoesBuilder).reset();
        verify(shoesBuilder).setProductInformation();
        verify(shoesBuilder).setProductSpecificInformation();
        verify(shoesBuilder).setProductPhotos();
        verifyNoMoreInteractions(shoesBuilder);
    }


    @Test
    @Order(2)
    void testReset_noReset_shoesIsNull() {
        shoesBuilder = new ShoesBuilder();
        assertNotEquals(new Shoes(), shoesBuilder.getShoes());
        assertNull(shoesBuilder.getShoes());
    }

    @Test
    @Order(2)
    void testReset_reset_emptyShoesCreated() {
        shoesBuilder.reset();
        assertEquals(new Shoes(), shoesBuilder.getShoes());
    }

    @Test
    @Order(3)
    void testSetProductInformation_productInformation_updateShoesWithProductInformation() {
        shoesBuilder = new ShoesBuilder(productDTO);
        shoesBuilder.setProductInformation();
        assertEquals(SHOES, shoesBuilder.getShoes().getCategory());
        assertEquals(GUCCI, shoesBuilder.getShoes().getBrand());
        assertEquals("Horsebit mid-heel slingback pumps", shoesBuilder.getShoes().getModel());
        assertEquals(VERY_GOOD, shoesBuilder.getShoes().getCondition());
        assertEquals("In very good shape", shoesBuilder.getShoes().getDetails());
    }


    @Test
    @Order(3)
    void testSetProductInformation_productInformation_doNotUpdateShoesWithSpecificInformation() {
        shoesBuilder = new ShoesBuilder(productDTO);
        shoesBuilder.setProductInformation();
        assertNull(shoesBuilder.getShoes().getShoesSize());
    }

    @Test
    @Order(4)
    void setProductSpecificInformation_shoesInformation_updateShoesWithShoesInformation() {
        shoesBuilder = new ShoesBuilder(productDTO);
        shoesBuilder.setProductSpecificInformation();
        assertEquals((short) 12, shoesBuilder.getShoes().getShoesSize());
    }

    @Test
    @Order(4)
    void setProductSpecificInformation_shoesInformation_doNotUpdateShoesWithShoesInformation() {
        shoesBuilder = new ShoesBuilder(productDTO);
        shoesBuilder.setProductSpecificInformation();
        assertNull(shoesBuilder.getShoes().getCategory());
        assertNull(shoesBuilder.getShoes().getBrand());
        assertNull(shoesBuilder.getShoes().getModel());
        assertNull(shoesBuilder.getShoes().getCondition());
        assertNull(shoesBuilder.getShoes().getDetails());
    }

    @Disabled
    @Test
    @Order(5)
    void setProductPhotos() {
    }

    @Test
    @Order(6)
    void getProduct_returnsExistingProduct() {
        Shoes shoesResult = new Shoes();
        shoesResult.setCategory(SHOES);
        shoesResult.setBrand(GUCCI);
        shoesResult.setModel("Horsebit mid-heel slingback pumps");
        shoesResult.setCondition(VERY_GOOD);
        shoesResult.setDetails("In very good shape");
        shoesResult.setShoesSize((short) 12);
        shoesResult.setBlemishPhotos(List.of());

        shoesBuilder.setShoes(shoesResult);

        assertEquals(shoesResult, shoesBuilder.getProduct());
    }


}