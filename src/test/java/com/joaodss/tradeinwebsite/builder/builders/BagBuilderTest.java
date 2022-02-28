package com.joaodss.tradeinwebsite.builder.builders;

import com.joaodss.tradeinwebsite.dao.Bag;
import com.joaodss.tradeinwebsite.dto.BagDTO;
import com.joaodss.tradeinwebsite.dto.ProductDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

import static com.joaodss.tradeinwebsite.enums.BagExtra.BOX;
import static com.joaodss.tradeinwebsite.enums.BagSize.MEDIUM;
import static com.joaodss.tradeinwebsite.enums.Brand.PRADA;
import static com.joaodss.tradeinwebsite.enums.Category.BAG;
import static com.joaodss.tradeinwebsite.enums.Condition.GOOD;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class BagBuilderTest {

    @InjectMocks
    @Spy
    private BagBuilder bagBuilder;

    private ProductDTO productDTO = new ProductDTO(
            "Bag",
            "Prada",
            "Re-Edition 2000 sequined Re-Nylon bag",
            "Good",
            "In good shape",
            new BagDTO("Medium", Set.of("Box")),
            null,
            List.of("one", "two")
    );


    @Test
    @Order(1)
    void testBuildFrom_productDTO_usesInternMethods() {
        bagBuilder.buildFrom(productDTO);
        verify(bagBuilder).buildFrom(productDTO);

        assertEquals(productDTO, bagBuilder.getProductDTO());
        verify(bagBuilder).getProductDTO();

        verify(bagBuilder).reset();
        verify(bagBuilder).setProductInformation();
        verify(bagBuilder).setProductSpecificInformation();
        verify(bagBuilder).setProductPhotos();
        verifyNoMoreInteractions(bagBuilder);
    }

    @Test
    @Order(2)
    void testReset_noReset_bagIsNull() {
        bagBuilder = new BagBuilder();
        assertNotEquals(new Bag(), bagBuilder.getBag());
        assertNull(bagBuilder.getBag());
    }

    @Test
    @Order(2)
    void testReset_reset_emptyBagCreated() {
        bagBuilder.reset();
        assertEquals(new Bag(), bagBuilder.getBag());
    }

    @Test
    @Order(3)
    void testSetProductInformation_productInformation_updateBagWithProductInformation() {
        bagBuilder = new BagBuilder(productDTO);
        bagBuilder.setProductInformation();
        assertEquals(BAG, bagBuilder.getBag().getCategory());
        assertEquals(PRADA, bagBuilder.getBag().getBrand());
        assertEquals("Re-Edition 2000 sequined Re-Nylon bag", bagBuilder.getBag().getModel());
        assertEquals(GOOD, bagBuilder.getBag().getCondition());
        assertEquals("In good shape", bagBuilder.getBag().getDetails());
    }

    @Test
    @Order(3)
    void testSetProductInformation_productInformation_doNotUpdateBagWithSpecificInformation() {
        bagBuilder = new BagBuilder(productDTO);
        bagBuilder.setProductInformation();
        assertNull(bagBuilder.getBag().getBagSize());
        assertNull(bagBuilder.getBag().getBagExtras());
    }

    @Test
    @Order(4)
    void setProductSpecificInformation_bagInformation_updateBagWithBagInformation() {
        bagBuilder = new BagBuilder(productDTO);
        bagBuilder.setProductSpecificInformation();
        assertEquals(MEDIUM, bagBuilder.getBag().getBagSize());
        assertEquals(Set.of(BOX), bagBuilder.getBag().getBagExtras());
    }

    @Test
    @Order(4)
    void setProductSpecificInformation_bagInformation_doNotUpdateBagWithBagInformation() {
        bagBuilder = new BagBuilder(productDTO);
        bagBuilder.setProductSpecificInformation();
        assertNull(bagBuilder.getBag().getCategory());
        assertNull(bagBuilder.getBag().getBrand());
        assertNull(bagBuilder.getBag().getModel());
        assertNull(bagBuilder.getBag().getCondition());
        assertNull(bagBuilder.getBag().getDetails());
    }

    @Disabled
    @Test
    @Order(5)
    void setProductPhotos() {
    }

    @Test
    @Order(6)
    void getProduct_returnsExistingProduct() {
        Bag bagResult = new Bag();
        bagResult.setCategory(BAG);
        bagResult.setBrand(PRADA);
        bagResult.setModel("Re-Edition 2000 sequined Re-Nylon bag");
        bagResult.setCondition(GOOD);
        bagResult.setDetails("In good shape");
        bagResult.setBagSize(MEDIUM);
        bagResult.setBlemishPhotos(List.of("one", "two"));

        bagBuilder.setBag(bagResult);

        assertEquals(bagResult, bagBuilder.getProduct());
    }
}