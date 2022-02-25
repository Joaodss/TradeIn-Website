package com.joaodss.tradeinwebsite.builder.director;

import com.joaodss.tradeinwebsite.dao.Bag;
import com.joaodss.tradeinwebsite.dao.Shoes;
import com.joaodss.tradeinwebsite.dto.BagDTO;
import com.joaodss.tradeinwebsite.dto.ProductDTO;
import com.joaodss.tradeinwebsite.dto.ShoesDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductCreatorTest {
    private final ProductCreator productCreator = new ProductCreator();

    private ProductDTO bagProductDTO;
    private ProductDTO shoesProductDTO;
    private ProductDTO invalidProductDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bagProductDTO = new ProductDTO(
                "Bag",
                "Prada",
                "Re-Edition 2000 sequined Re-Nylon bag",
                "Good",
                "In good shape",
                new BagDTO("Medium", Set.of()),
                null,
                List.of()
        );
        shoesProductDTO = new ProductDTO(
                "Shoes",
                "Gucci",
                "Horsebit mid-heel slingback pumps",
                "Very Good",
                "In very good shape",
                null,
                new ShoesDTO((short) 12),
                List.of()
        );
        invalidProductDTO = new ProductDTO(
                "---",
                "Test",
                "Non existent",
                "Poor",
                "Just a test",
                null,
                null,
                List.of()
        );
    }


    @Test
    void testCreateProductFrom_bagProduct_returnObjectTypeBag() {
        assertEquals(Bag.class, productCreator.createProductFrom(bagProductDTO).getClass());
    }

    @Test
    void testCreateProductFrom_shoesProduct_returnObjectTypeBagShoes() {
        assertEquals(Shoes.class, productCreator.createProductFrom(shoesProductDTO).getClass());
    }

    @Test
    void testCreateProductFrom_invalidProduct_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> productCreator.createProductFrom(invalidProductDTO));
    }


}