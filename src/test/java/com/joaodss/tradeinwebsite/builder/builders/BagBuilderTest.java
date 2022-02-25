package com.joaodss.tradeinwebsite.builder.builders;

import com.joaodss.tradeinwebsite.dao.Bag;
import com.joaodss.tradeinwebsite.dto.BagDTO;
import com.joaodss.tradeinwebsite.dto.ProductDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;

import java.util.List;
import java.util.Set;

import static com.joaodss.tradeinwebsite.enums.Brand.PRADA;
import static com.joaodss.tradeinwebsite.enums.Category.BAG;
import static com.joaodss.tradeinwebsite.enums.Condition.GOOD;
import static org.junit.jupiter.api.Assertions.*;

class BagBuilderTest {

    @InjectMocks
    @Spy
    private BagBuilder bagBuilder;

    private ProductDTO product1 = new ProductDTO(
            "Bag",
            "Prada",
            "Re-Edition 2000 sequined Re-Nylon bag",
            "Good",
            "In good shape",
            new BagDTO("Medium", Set.of()),
            null,
            List.of()
    );

    @BeforeEach
    void setUp() {
        bagBuilder = new BagBuilder();
    }


    @Test
    void buildFrom() {
    }

    @Test
    void testReset_noReset_bagIsNull() {
        assertNotEquals(new Bag(), bagBuilder.getBag());
        assertNull(bagBuilder.getBag());
    }

    @Test
    void testReset_reset_emptyBagCreated() {
        bagBuilder.reset();
        assertEquals(new Bag(), bagBuilder.getBag());
    }

    @Test
    void testSetProductInformation_productInformation_updateBagWithProductInformation() {
        bagBuilder = new BagBuilder(product1);
        bagBuilder.setProductInformation();
        assertEquals(BAG, bagBuilder.getBag().getCategory());
        assertEquals(PRADA, bagBuilder.getBag().getBrand());
        assertEquals("Re-Edition 2000 sequined Re-Nylon bag", bagBuilder.getBag().getModel());
        assertEquals(GOOD, bagBuilder.getBag().getCondition());
        assertEquals("In good shape", bagBuilder.getBag().getDetails());
    }

    @Test
    void testSetProductInformation_productInformation_doNotUpdateBagWithSpecificInformation() {
        bagBuilder = new BagBuilder(product1);
        bagBuilder.setProductInformation();
        assertNull(bagBuilder.getBag().getBagSize());
        assertNull(bagBuilder.getBag().getBagExtras());
    }

    @Test
    void setProductSpecificInformation() {
    }

    @Test
    void setProductPhotos() {
    }

    @Test
    void getProduct() {
    }
}