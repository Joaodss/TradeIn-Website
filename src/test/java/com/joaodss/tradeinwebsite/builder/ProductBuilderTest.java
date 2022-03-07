package com.joaodss.tradeinwebsite.builder;

import com.joaodss.tradeinwebsite.dao.request.Bag;
import com.joaodss.tradeinwebsite.dao.request.Product;
import com.joaodss.tradeinwebsite.dao.request.Shoes;
import com.joaodss.tradeinwebsite.dao.specification.Brand;
import com.joaodss.tradeinwebsite.dao.specification.Category;
import com.joaodss.tradeinwebsite.dto.request.BagDTO;
import com.joaodss.tradeinwebsite.dto.request.ProductDTO;
import com.joaodss.tradeinwebsite.dto.request.ShoesDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Set;

import static com.joaodss.tradeinwebsite.enums.Condition.GOOD;
import static com.joaodss.tradeinwebsite.enums.RequestStatus.PENDING;
import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(OrderAnnotation.class)
class ProductBuilderTest {
    private final Category bag = new Category(1L, "Bag", Set.of());
    private final Category shoes = new Category(2L, "Shoes", Set.of());
    private final Brand chanel = new Brand(1L, "CHANEL", Set.of());
    private final ProductDTO shoesProductDTO = new ProductDTO(
            "Shoes",
            "Gucci",
            "Horsebit mid-heel slingback pumps",
            "Very Good",
            "In very good shape",
            "link to google drive",
            null,
            new ShoesDTO((short) 12)
    );
    private final Product shoes1 = new Shoes(
            PENDING,
            bag,
            chanel,
            "Simple shoes",
            GOOD,
            "No details",
            "link",
            (short) 36
    );
    private ProductBuilder productBuilder;
    private ProductDTO bagProductDTO;

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
    }

    @Test
    @Order(1)
    void testBuildProductFrom_validProductCategory_dontThrowException() {
        assertDoesNotThrow(() -> productBuilder.buildProductFrom(bagProductDTO));
    }

    @Test
    @Order(1)
    void testBuildProductFrom_invalidProductCategory_throwException() {
        bagProductDTO.setCategory("InvalidCategory");
        assertThrows(IllegalArgumentException.class, () -> productBuilder.buildProductFrom(bagProductDTO));
    }

    @Test
    @Order(1)
    void testBuildProductFrom_nullProduct_throwNullPointerException() {
        bagProductDTO.setCategory(null);
        assertThrows(NullPointerException.class, () -> productBuilder.buildProductFrom(bagProductDTO));
    }

    @Test
    @Order(2)
    void testBuildProductFrom_bagProduct_returnObjectTypeBag() {
        productBuilder.buildProductFrom(bagProductDTO);
        assertEquals(Bag.class, productBuilder.getProduct().getClass());
    }

    @Test
    @Order(2)
    void testBuildProductFrom_shoesProduct_returnObjectTypeBagShoes() {
        productBuilder.buildProductFrom(shoesProductDTO);
        assertEquals(Shoes.class, productBuilder.getProduct().getClass());
    }

    @Test
    @Order(3)
    void testBuild_ProductCreated_returnProduct() {
        productBuilder.setProduct(shoes1);
        assertEquals(shoes1, productBuilder.build());
    }

    @Test
    @Order(3)
    void testBuild_noProductCreated_returnNull() {
        assertNull(productBuilder.build());
    }

}