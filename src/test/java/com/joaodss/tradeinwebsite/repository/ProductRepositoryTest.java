package com.joaodss.tradeinwebsite.repository;

import com.joaodss.tradeinwebsite.dao.Bag;
import com.joaodss.tradeinwebsite.dao.Product;
import com.joaodss.tradeinwebsite.dao.Shoes;
import com.joaodss.tradeinwebsite.dao.TradeInRequest;
import com.joaodss.tradeinwebsite.databaseutils.DbResetUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.joaodss.tradeinwebsite.enums.BagSize.MEDIUM;
import static com.joaodss.tradeinwebsite.enums.Brand.CHANEL;
import static com.joaodss.tradeinwebsite.enums.Brand.GUCCI;
import static com.joaodss.tradeinwebsite.enums.Category.BAG;
import static com.joaodss.tradeinwebsite.enums.Category.SHOES;
import static com.joaodss.tradeinwebsite.enums.Condition.GOOD;
import static com.joaodss.tradeinwebsite.enums.Condition.USED;
import static com.joaodss.tradeinwebsite.enums.RequestStatus.PENDING;
import static com.neovisionaries.i18n.CountryCode.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class ProductRepositoryTest {
    private final ApplicationContext applicationContext;
    private final TradeInRequestRepository tradeInRequestRepository;
    private final ProductRepository productRepository;

    private Product bag;
    private Product shoes;
    private TradeInRequest tradeInRequest1;
    private TradeInRequest tradeInRequest2;
    private TradeInRequest newTradeInRequest = new TradeInRequest(
            "Maria",
            "Doe",
            "maria.doe@email.com",
            "9999999999",
            ES,
            PENDING
    );
    private Product newBag = new Bag(
            BAG,
            GUCCI,
            "Simple bag",
            USED,
            "No details",
            List.of(),
            MEDIUM,
            Set.of()
    );
    private Product newShoes = new Shoes(
            SHOES,
            CHANEL,
            "Simple shoes",
            GOOD,
            "No details",
            List.of(),
            (short) 36
    );

    @BeforeEach
    void setUp() {
        tradeInRequest1 = new TradeInRequest(
                "John",
                "Doe",
                "john.doe@email.com",
                "12345678",
                US,
                PENDING
        );
        bag = new Bag(
                BAG,
                GUCCI,
                "Simple bag",
                USED,
                "No details",
                List.of(),
                MEDIUM,
                Set.of()
        );
        tradeInRequest1.addProduct(bag);


        tradeInRequest2 = new TradeInRequest(
                "Robin",
                "Doe",
                "robin.doe@email.com",
                "987654321",
                PT,
                PENDING
        );
        shoes = new Shoes(
                SHOES,
                CHANEL,
                "Simple shoes",
                GOOD,
                "No details",
                List.of(),
                (short) 36
        );
        tradeInRequest2.addProduct(shoes);
        tradeInRequestRepository.saveAll(List.of(tradeInRequest1, tradeInRequest2));
    }

    @AfterEach
    void tearDown() throws SQLException {
        tradeInRequestRepository.deleteAll();
        productRepository.deleteAll();
        DbResetUtil.resetAutoIncrementColumns(applicationContext, "trade_in_request", "product");
    }


    // ------------------------------ CRUD TESTING ------------------------------
    @Test
    @Order(1)
    void testCount_numberOfProductsInDatabase_correctAmount() {
        assertEquals(2, productRepository.count());
    }

    // -------------------- Create --------------------
    @Test
    @Order(2)
    void testCreateProduct_saveNewTradeInRequestWithNewProduct_PersistProduct_storedInRepository() {
        long initialSize = productRepository.count();

        newTradeInRequest.addProduct(newBag);
        tradeInRequestRepository.save(newTradeInRequest);

        assertEquals(initialSize + 1, productRepository.count());
    }

    @Test
    @Order(2)
    void testCreateProduct_saveExistingTradeInRequestsWithNewProduct_storedInRepository() {
        newTradeInRequest.addProduct(newBag);
        tradeInRequestRepository.save(newTradeInRequest);
        long initialSize = productRepository.count();

        newTradeInRequest.addProduct(newShoes);
        tradeInRequestRepository.save(newTradeInRequest);

        assertEquals(initialSize + 1, productRepository.count());
        assertEquals(2, newTradeInRequest.getProducts().size());
    }

    // -------------------- Read --------------------
    @Test
    @Order(3)
    void testReadProduct_findAll_returnsListOfObjectsNotEmpty() {
        List<Product> allElements = productRepository.findAll();
        assertFalse(allElements.isEmpty());
    }

    @Test
    @Order(3)
    void testReadProduct_findById_validId_returnCorrectObject() {
        Product element = productRepository.findById(2L)
                .orElseThrow(() -> new RuntimeException("Element not found"));
        assertEquals(shoes, element);
    }

    @Test
    @Order(3)
    void testReadProduct_findById_invalidId_returnEmpty() {
        Optional<Product> element = productRepository.findById(9999L);
        assertTrue(element.isEmpty());
    }

    // -------------------- Read from Parent --------------------
    @Test
    @Order(3)
    void testReadProduct_byParentIdJoined_returnCorrectObject() {
        TradeInRequest element = tradeInRequestRepository.findByIdJoined(2L)
                .orElseThrow(() -> new RuntimeException("Element not found"));
        assertEquals(List.of(shoes), element.getProducts());
    }

    // -------------------- Update --------------------
    @Test
    @Order(4)
    void testUpdateProduct_changeDescription_newDescriptionEqualsDefinedValue() {
        Product element = productRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Element not found"));

        assertNotEquals("New details", element.getDetails());
        element.setDetails("New details");
        productRepository.save(element);

        Product updatedElement = productRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Element not found"));
        assertEquals("New details", updatedElement.getDetails());
    }

    // -------------------- Update from Parent --------------------
    @Test
    @Order(4)
    void testUpdateProduct_fromParent_changeDescription_newDescriptionEqualsDefinedValue() {
        Product element = productRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Element not found"));

        assertNotEquals("New details", element.getDetails());
        element.setDetails("New details");

        TradeInRequest parentElement = tradeInRequestRepository.findByIdJoined(element.getTradeInRequest().getId())
                .orElseThrow(() -> new RuntimeException("Parent Element not found"));
        parentElement.updateProduct(element);
        tradeInRequestRepository.save(parentElement);

        Product updatedElement = productRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Element not found"));
        assertEquals("New details", updatedElement.getDetails());
    }

    // -------------------- Delete --------------------
    @Test
    @Order(5)
    void testDeleteProduct_validId_deletedFromRepository() {
        long initialSize = productRepository.count();

        productRepository.deleteById(2L);

        assertEquals(initialSize - 1, productRepository.count());
    }

    @Test
    @Order(5)
    void testDeleteProduct_invalidId_deletedFromRepository() {
        assertThrows(Exception.class, () -> productRepository.deleteById(9999L));
    }

    // -------------------- Delete from Parent --------------------
    @Test
    @Order(5)
    void testDeleteProduct_fromParent_deletedFromRepository() {
        Product element = productRepository.findById(2L)
                .orElseThrow(() -> new RuntimeException("Element not found"));
        TradeInRequest parentElement = tradeInRequestRepository.findByIdJoined(element.getTradeInRequest().getId())
                .orElseThrow(() -> new RuntimeException("Parent Element not found"));
        parentElement.addProduct(newBag);
        tradeInRequestRepository.save(parentElement);

        long initialSize = productRepository.count();

        parentElement.removeProduct(element);
        tradeInRequestRepository.save(parentElement);

        assertEquals(initialSize - 1, tradeInRequestRepository.count());
    }

    @Test
    @Order(5)
    void testDeleteProduct_fromParent_lastProduct_throwException() {
        Product element = productRepository.findById(2L)
                .orElseThrow(() -> new RuntimeException("Element not found"));
        TradeInRequest parentElement = tradeInRequestRepository.findByIdJoined(element.getTradeInRequest().getId())
                .orElseThrow(() -> new RuntimeException("Parent Element not found"));

        assertThrows(IllegalStateException.class, () -> parentElement.removeProduct(element));
    }
}