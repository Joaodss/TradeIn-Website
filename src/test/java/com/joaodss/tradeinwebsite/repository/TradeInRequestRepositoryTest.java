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
class TradeInRequestRepositoryTest {
    private final ApplicationContext applicationContext;
    private final TradeInRequestRepository tradeInRequestRepository;

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
        var initialSize = tradeInRequestRepository.count();

        newTradeInRequest.addProduct(newBag);
        newTradeInRequest.addProduct(newShoes);
        tradeInRequestRepository.save(newTradeInRequest);

        assertEquals(initialSize + 1, tradeInRequestRepository.count());
    }

    // -------------------- Read --------------------
    @Test
    @Order(3)
    void testReadTradeInRequest_findAll_returnsListOfObjectsNotEmpty() {
        var allElements = tradeInRequestRepository.findAll();
        assertFalse(allElements.isEmpty());
    }

    @Test
    @Order(3)
    void testReadTradeInRequest_findById_validId_returnsObjectsWithCorrectValue() {
        var element = tradeInRequestRepository.findById(2L)
                .orElseThrow(() -> new RuntimeException("Element not found"));
        assertEquals(tradeInRequest2, element);
    }

    @Test
    @Order(3)
    void testReadTradeInRequest_findById_invalidId_returnsEmpty() {
        var element = tradeInRequestRepository.findById(9999L);
        assertTrue(element.isEmpty());
    }

    // -------------------- Update --------------------
    @Test
    @Order(4)
    void testUpdateTradeInRequest_changeName_newNameEqualsDefinedValue() {
        var element = tradeInRequestRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Element not found"));

        assertNotEquals("Arthur", element.getFirstName());
        assertNotEquals("Smith", element.getLastName());
        element.setFirstName("Arthur");
        element.setLastName("Smith");
        tradeInRequestRepository.save(element);

        var updatedElement = tradeInRequestRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Element not found"));
        assertEquals("Arthur", updatedElement.getFirstName());
        assertEquals("Smith", updatedElement.getLastName());
    }

    // -------------------- Delete --------------------
    @Test
    @Order(5)
    void testDeleteTradeInRequest_validId_deletedFromRepository() {
        var initialSize = tradeInRequestRepository.count();

        tradeInRequestRepository.deleteById(2L);

        assertEquals(initialSize - 1, tradeInRequestRepository.count());
    }

    @Test
    @Order(5)
    void testDeleteTradeInRequest_invalidId_deletedFromRepository() {
        assertThrows(Exception.class, () -> tradeInRequestRepository.deleteById(9999L));
    }
}