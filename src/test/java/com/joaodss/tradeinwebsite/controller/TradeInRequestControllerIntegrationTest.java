package com.joaodss.tradeinwebsite.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joaodss.tradeinwebsite.dao.Bag;
import com.joaodss.tradeinwebsite.dao.Product;
import com.joaodss.tradeinwebsite.dao.Shoes;
import com.joaodss.tradeinwebsite.dao.TradeInRequest;
import com.joaodss.tradeinwebsite.databaseutils.DbResetUtil;
import com.joaodss.tradeinwebsite.dto.ResponseTradeInRequestDTO;
import com.joaodss.tradeinwebsite.repository.ProductRepository;
import com.joaodss.tradeinwebsite.repository.TradeInRequestRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import static com.joaodss.tradeinwebsite.enums.BagExtra.BOX;
import static com.joaodss.tradeinwebsite.enums.BagExtra.MIRROR;
import static com.joaodss.tradeinwebsite.enums.BagSize.MEDIUM;
import static com.joaodss.tradeinwebsite.enums.Brand.CHANEL;
import static com.joaodss.tradeinwebsite.enums.Brand.GUCCI;
import static com.joaodss.tradeinwebsite.enums.Category.BAG;
import static com.joaodss.tradeinwebsite.enums.Category.SHOES;
import static com.joaodss.tradeinwebsite.enums.Condition.GOOD;
import static com.joaodss.tradeinwebsite.enums.Condition.USED;
import static com.joaodss.tradeinwebsite.enums.RequestStatus.PENDING;
import static com.neovisionaries.i18n.CountryCode.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class TradeInRequestControllerIntegrationTest {
    private final ApplicationContext applicationContext;
    private final WebApplicationContext webApplicationContext;
    private final TradeInRequestRepository tradeInRequestRepository;
    private final ProductRepository productRepository;
    private final TradeInRequestController tradeInRequestController;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String baseUrl = "/api/v1/trade-in-request";

    private MockMvc mockMvc;

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
            List.of("One photo", "Two photos"),
            MEDIUM,
            Set.of(BOX, MIRROR)
    );
    private Product newShoes = new Shoes(
            SHOES,
            CHANEL,
            "Simple shoes",
            GOOD,
            "No details",
            List.of("One photo", "Two photos", "Three photos"),
            (short) 36
    );

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

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


    // ------------------------------ GET ALL ------------------------------
    @Test
    @Order(1)
    void testGetAllTradeInRequests_repositoryNotEmpty_returnOkResponseStatus() throws Exception {
        mockMvc.perform(
                        get(baseUrl)
                )
                .andExpect(status().isOk());
    }

    @Test
    @Order(1)
    void testGetAllTradeInRequests_repositoryNotEmpty_returnCorrectResponseBody() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                        get(baseUrl)
                )
                .andExpectAll(
                        jsonPath("$", hasSize(2)),
                        jsonPath(
                                "$[*].email",
                                containsInAnyOrder("john.doe@email.com", "robin.doe@email.com")
                        )
                )
                .andReturn();

        String jsonResult = mvcResult.getResponse().getContentAsString();
        String jsonExpected = objectMapper.writeValueAsString(
                List.of(
                        new ResponseTradeInRequestDTO(tradeInRequest1),
                        new ResponseTradeInRequestDTO(tradeInRequest2)
                )
        );
        assertEquals(jsonResult, jsonExpected);
    }

    @Test
    @Order(1)
    void testGetAllTradeInRequests_repositoryEmpty_returnEmptyList() throws Exception {
        tearDown();
        MvcResult mvcResult = mockMvc.perform(
                        get(baseUrl)
                )
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$", hasSize(0))
                )
                .andReturn();

        String jsonResult = mvcResult.getResponse().getContentAsString();
        String jsonExpected = objectMapper.writeValueAsString(
                List.of()
        );
        assertEquals(jsonResult, jsonExpected);
    }


    // ------------------------------ GET BY EMAIL ------------------------------
    @Test
    @Order(2)
    void testGetTradeInRequestsByEmail_emailParameterValid_returnOkResponseStatus() throws Exception {
        mockMvc.perform(
                        get(baseUrl)
                                .param("email", "robin.doe@email.com")
                )
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    void testGetTradeInRequestsByEmail_passNull_returnBadRequest() throws Exception {
        mockMvc.perform(
                        get(baseUrl)
                                .param("email", (String) null)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(2)
    void testGetTradeInRequestsByEmail_emailExists_returnCorrectResponseBody() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                        get(baseUrl)
                                .param("email", "robin.doe@email.com")
                )
                .andExpectAll(
                        jsonPath("$", hasSize(1)),
                        jsonPath("$[*].email", containsInAnyOrder("robin.doe@email.com"))
                )
                .andReturn();

        String jsonResult = mvcResult.getResponse().getContentAsString();
        String jsonExpected = objectMapper.writeValueAsString(
                List.of(new ResponseTradeInRequestDTO(tradeInRequest2))
        );
        assertEquals(jsonResult, jsonExpected);
    }

    @Test
    @Order(2)
    void testGetTradeInRequestsByEmail_emailDoesNotExists_returnEmptyList() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                        get(baseUrl)
                                .param("email", "")
                )
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$", hasSize(0))
                )
                .andReturn();

        String jsonResult = mvcResult.getResponse().getContentAsString();
        String jsonExpected = objectMapper.writeValueAsString(
                List.of()
        );
        assertEquals(jsonResult, jsonExpected);
    }


    // ------------------------------ GET BY ID ------------------------------
    @Test
    @Order(3)
    void testGetTradeInRequestById_idExists_returnOkResponseStatus() throws Exception {
        mockMvc.perform(
                        get(baseUrl + "/" + tradeInRequest1.getId())
                )
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    void testGetTradeInRequestById_idDoesNotExists_returnNotFound() throws Exception {
        mockMvc.perform(
                        get(baseUrl + "/" + 999L)
                )
                .andExpect(status().isNotFound());
    }

    // TODO: Validate negatives and 0
//    @Test
//    @Order(3)
//    void testGetTradeInRequestById_idInvalid_returnBadRequest() throws Exception {
//        mockMvc.perform(
//                        get(baseUrl + "/" + -1L)
//                )
//                .andExpect(status().isBadRequest());
//    }

    @Test
    @Order(3)
    void testGetTradeInRequestById_idExists_returnCorrectResponseBody() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                        get(baseUrl + "/" + tradeInRequest1.getId())
                )
                .andExpectAll(
                        jsonPath("$.firstName", is(tradeInRequest1.getFirstName())),
                        jsonPath("$.email", is(tradeInRequest1.getEmail())),
                        jsonPath("$.products", hasSize(1))
                )
                .andReturn();

        String jsonResult = mvcResult.getResponse().getContentAsString();
        String jsonExpected = objectMapper.writeValueAsString(
                new ResponseTradeInRequestDTO(tradeInRequest1)
        );
        assertEquals(jsonResult, jsonExpected);
    }


    // ------------------------------ CREATE ------------------------------
    @Test
    void createTradeInRequests() {
    }

    // ------------------------------ DELETE BY ID ------------------------------
    @Test
    void deleteTradeInRequestsById() {
    }
}