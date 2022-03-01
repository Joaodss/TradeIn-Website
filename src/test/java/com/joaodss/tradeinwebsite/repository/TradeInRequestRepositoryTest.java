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

import static com.joaodss.tradeinwebsite.enums.BagExtra.DUSTBAG;
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
class TradeInRequestRepositoryTest {
    private final ApplicationContext applicationContext;
    private final TradeInRequestRepository tradeInRequestRepository;
    private final ProductRepository productRepository;

    private Product bag;
    private Product shoes;
    private TradeInRequest tradeInRequest1;
    private TradeInRequest tradeInRequest2;
    private final TradeInRequest newTradeInRequest = new TradeInRequest(
            "Maria",
            "Doe",
            "maria.doe@email.com",
            "9999999999",
            ES
    );
    private final Product newBag = new Bag(
            PENDING,
            BAG,
            GUCCI,
            "Simple bag",
            USED,
            "No details",
            "link to photos",
            MEDIUM,
            Set.of()
    );
    private final Product newShoes = new Shoes(
            PENDING,
            SHOES,
            CHANEL,
            "Simple shoes",
            GOOD,
            "No details",
            "link to photos",
            (short) 36
    );

    @BeforeEach
    void setUp() {
        tradeInRequest1 = new TradeInRequest(
                "John",
                "Doe",
                "john.doe@email.com",
                "12345678",
                US
        );
        bag = new Bag(
                PENDING,
                BAG,
                GUCCI,
                "Simple bag",
                USED,
                "No details",
                "link",
                MEDIUM,
                Set.of()
        );
        tradeInRequest1.addProduct(bag);


        tradeInRequest2 = new TradeInRequest(
                "Robin",
                "Doe",
                "robin.doe@email.com",
                "987654321",
                PT
        );
        shoes = new Shoes(
                PENDING,
                SHOES,
                CHANEL,
                "Simple shoes",
                GOOD,
                "No details",
                "link",
                (short) 36
        );
        tradeInRequest2.addProduct(shoes);
        tradeInRequestRepository.saveAll(List.of(tradeInRequest1, tradeInRequest2));
    }

    @AfterEach
    void tearDown() throws SQLException {
        tradeInRequestRepository.deleteAll();
        DbResetUtil.resetAutoIncrementColumns(applicationContext, "trade_in_request", "product");
    }


    // ------------------------------ CRUD TESTING ------------------------------
    @Test
    @Order(1)
    void testCount_numberOfTradeInRequestsInDatabase_correctAmount() {
        assertEquals(2, tradeInRequestRepository.count());
    }

    // -------------------- Create --------------------
    @Test
    @Order(2)
    void testCreateTradeInRequest_saveNewTradeInRequests_storedInRepository() {
        long initialSize = tradeInRequestRepository.count();

        newTradeInRequest.addProduct(newBag);
        newTradeInRequest.addProduct(newShoes);
        tradeInRequestRepository.save(newTradeInRequest);

        assertEquals(initialSize + 1, tradeInRequestRepository.count());
    }

    // -------------------- Create Product --------------------
    @Test
    @Order(3)
    void testCreateProduct_saveNewTradeInRequestWithNewProduct_PersistProduct_storedInRepository() {
        long initialSize = productRepository.count();

        newTradeInRequest.addProduct(newBag);
        tradeInRequestRepository.save(newTradeInRequest);

        assertEquals(initialSize + 1, productRepository.count());
    }

    @Test
    @Order(3)
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
    @Order(4)
    void testReadTradeInRequest_findAll_returnsListOfObjectsNotEmpty() {
        List<TradeInRequest> allElements = tradeInRequestRepository.findAll();
        assertFalse(allElements.isEmpty());
    }

    @Test
    @Order(4)
    void testReadTradeInRequest_findById_validId_returnsObjectsWithCorrectValue() {
        TradeInRequest element = tradeInRequestRepository.findById(2L)
                .orElseThrow(() -> new RuntimeException("Element not found"));
        assertEquals(tradeInRequest2, element);
    }

    @Test
    @Order(4)
    void testReadTradeInRequest_findById_invalidId_returnsEmpty() {
        Optional<TradeInRequest> element = tradeInRequestRepository.findById(9999L);
        assertTrue(element.isEmpty());
    }

    // -------------------- Read with Product --------------------
    @Test
    @Order(5)
    void testReadTradeInRequest_AllJoined_returnObjectsWithChild() {
        List<TradeInRequest> elements = tradeInRequestRepository.findAllJoined();
        assertEquals(List.of(bag), elements.get(0).getProducts());
        assertEquals(List.of(shoes), elements.get(1).getProducts());
    }

    @Test
    @Order(5)
    void testReadTradeInRequest_byParentIdJoined_returnObjectWithChild() {
        TradeInRequest element = tradeInRequestRepository.findByIdJoined(2L)
                .orElseThrow(() -> new RuntimeException("Element not found"));
        assertEquals(List.of(shoes), element.getProducts());
    }

    @Test
    @Order(5)
    void testReadTradeInRequest_byParentEmailJoined_returnObjectWithChild() {
        List<TradeInRequest> elements = tradeInRequestRepository.findByEmailJoined("john.doe@email.com");
        assertEquals(List.of(bag), elements.get(0).getProducts());
    }

    // -------------------- Update --------------------
    @Test
    @Order(6)
    void testUpdateTradeInRequest_changeName_newNameEqualsDefinedValue() {
        TradeInRequest element = tradeInRequestRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Element not found"));

        assertNotEquals("Arthur", element.getFirstName());
        assertNotEquals("Smith", element.getLastName());
        element.setFirstName("Arthur");
        element.setLastName("Smith");
        tradeInRequestRepository.save(element);

        TradeInRequest updatedElement = tradeInRequestRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Element not found"));
        assertEquals("Arthur", updatedElement.getFirstName());
        assertEquals("Smith", updatedElement.getLastName());
    }

    // -------------------- Update Product --------------------
    @Test
    @Order(7)
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
    @Order(8)
    void testDeleteTradeInRequest_validId_deletedFromRepository() {
        long initialSize = tradeInRequestRepository.count();

        tradeInRequestRepository.deleteById(2L);

        assertEquals(initialSize - 1, tradeInRequestRepository.count());
    }

    @Test
    @Order(8)
    void testDeleteTradeInRequest_invalidId_deletedFromRepository() {
        assertThrows(Exception.class, () -> tradeInRequestRepository.deleteById(9999L));
    }

    // -------------------- Delete from Parent --------------------
    @Test
    @Order(9)
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
    @Order(9)
    void testDeleteProduct_fromParent_lastProduct_throwException() {
        Product element = productRepository.findById(2L)
                .orElseThrow(() -> new RuntimeException("Element not found"));
        TradeInRequest parentElement = tradeInRequestRepository.findByIdJoined(element.getTradeInRequest().getId())
                .orElseThrow(() -> new RuntimeException("Parent Element not found"));

        assertThrows(IllegalStateException.class, () -> parentElement.removeProduct(element));
    }

}