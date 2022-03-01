package com.joaodss.tradeinwebsite.builder;

import com.joaodss.tradeinwebsite.dao.Bag;
import com.joaodss.tradeinwebsite.dao.Shoes;
import com.joaodss.tradeinwebsite.dto.BagDTO;
import com.joaodss.tradeinwebsite.dto.ProductDTO;
import com.joaodss.tradeinwebsite.dto.ShoesDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProductBuilderTest {
    private ProductBuilder productBuilder;
    private ProductDTO bagProductDTO;
    private ProductDTO shoesProductDTO;

    @BeforeEach
    void setUp() {
        productBuilder = new ProductBuilder();
        bagProductDTO = new ProductDTO(
                "Bag",
                "Prada",
                "Re-Edition 2000 sequined Re-Nylon bag",
                "Good",
                "In good shape",
                "link to google drive",
                new BagDTO("Medium", Set.of()),
                null
        );
        shoesProductDTO = new ProductDTO(
                "Shoes",
                "Gucci",
                "Horsebit mid-heel slingback pumps",
                "Very Good",
                "In very good shape",
                "link to google drive",
                null,
                new ShoesDTO((short) 12)
        );
    }

    @Test
    void testCreateProductFrom_validProductCategory_dontThrow() {
        assertDoesNotThrow(() -> productBuilder.buildProductFrom(bagProductDTO));
    }

    @Test
    void testCreateProductFrom_invalidProductCategory_throwIllegalArgumentException() {
        bagProductDTO.setCategory("InvalidCategory");
        assertThrows(IllegalArgumentException.class, () -> productBuilder.buildProductFrom(bagProductDTO));
    }

    @Test
    void testCreateProductFrom_nullProduct_throwNullPointerException() {
        bagProductDTO.setCategory(null);
        assertThrows(NullPointerException.class, () -> productBuilder.buildProductFrom(bagProductDTO));
    }

    @Test
    void testBuild_bagProduct_returnObjectTypeBag() {
        assertEquals(Bag.class, productBuilder.buildProductFrom(bagProductDTO).build().getClass());
    }

    @Test
    void testBuild_shoesProduct_returnObjectTypeBagShoes() {
        assertEquals(Shoes.class, productBuilder.buildProductFrom(shoesProductDTO).build().getClass());
    }

    @Test
    void testBuild_noProductInitialized_returnNull() {
        assertNull(productBuilder.build());
    }


}