package com.joaodss.tradeinwebsite.utils;

import com.joaodss.tradeinwebsite.dao.Bag;
import com.joaodss.tradeinwebsite.dao.Product;
import com.joaodss.tradeinwebsite.dao.Shoes;
import com.joaodss.tradeinwebsite.dao.TradeInRequest;
import com.joaodss.tradeinwebsite.repository.TradeInRequestRepository;
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
import static com.neovisionaries.i18n.CountryCode.PT;
import static com.neovisionaries.i18n.CountryCode.US;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
class DbResetUtilTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private TradeInRequestRepository tradeInRequestRepository;


    private Product bag;
    private Product shoes;
    private TradeInRequest tradeInRequest1;
    private TradeInRequest tradeInRequest2;

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
    }

    @AfterEach
    void tearDown() throws SQLException {
        tradeInRequestRepository.deleteAll();
        DbResetUtil.resetAutoIncrementColumns(applicationContext, "trade_in_request", "product");
    }


    @Test
    @Order(1)
    void testDbTestUtility_deleteAllTradeInRequestsCreateNewTradeInRequests_noAutoIncrementReset_idKeepsIncrementing() {
        assertEquals(0, tradeInRequestRepository.count());

        // Create First
        tradeInRequestRepository.save(tradeInRequest1);

        // Read First
        assertEquals(1, tradeInRequestRepository.findAll().get(0).getId());
        assertEquals("john.doe@email.com", tradeInRequestRepository.findAll().get(0).getEmail());

        // Delete All
        tradeInRequestRepository.deleteAll();
        assertEquals(0, tradeInRequestRepository.count());

        // Create Second
        tradeInRequestRepository.save(tradeInRequest2);

        // Read Second
        assertNotEquals(1, tradeInRequestRepository.findAll().get(0).getId());
        assertEquals(2, tradeInRequestRepository.findAll().get(0).getId());
        assertEquals("robin.doe@email.com", tradeInRequestRepository.findAll().get(0).getEmail());
    }

    @Test
    @Order(2)
    void testDbTestUtility_deleteAllTradeInRequestsCreateNewTradeInRequests_autoIncrementReset_sameId() throws SQLException {
        assertEquals(0, tradeInRequestRepository.count());

        // Create First
        tradeInRequestRepository.save(tradeInRequest1);

        // Read First
        assertEquals(1, tradeInRequestRepository.count());

        var firstAdmin = tradeInRequestRepository.findById(1L);
        assertTrue(firstAdmin.isPresent());
        assertEquals(1, firstAdmin.get().getId());
        assertEquals("John", firstAdmin.get().getFirstName());

        // Delete All
        tradeInRequestRepository.deleteAll();
        DbResetUtil.resetAutoIncrementColumns(applicationContext, "trade_in_request");
        assertEquals(0, tradeInRequestRepository.count());

        // Create Second
        tradeInRequestRepository.save(tradeInRequest2);

        // Read Second
        assertEquals(1, tradeInRequestRepository.count());

        var secondAdmin = tradeInRequestRepository.findById(1L);
        assertTrue(secondAdmin.isPresent());
        assertEquals(1, secondAdmin.get().getId());
        assertEquals("Robin", secondAdmin.get().getFirstName());
    }
}
