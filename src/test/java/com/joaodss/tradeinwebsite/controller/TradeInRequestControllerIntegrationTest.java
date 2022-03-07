package com.joaodss.tradeinwebsite.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joaodss.tradeinwebsite.dao.request.Bag;
import com.joaodss.tradeinwebsite.dao.request.Product;
import com.joaodss.tradeinwebsite.dao.request.Shoes;
import com.joaodss.tradeinwebsite.dao.request.TradeInRequest;
import com.joaodss.tradeinwebsite.databaseutils.DbResetUtil;
import com.joaodss.tradeinwebsite.dto.request.*;
import com.joaodss.tradeinwebsite.repository.request.ProductRepository;
import com.joaodss.tradeinwebsite.repository.request.TradeInRequestRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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
import static com.neovisionaries.i18n.CountryCode.PT;
import static com.neovisionaries.i18n.CountryCode.US;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String baseUrl = "/api/v1/trade-in-request";

    private MockMvc mockMvc;


    private String tradeInRequestDTOBody;

    private Product bag;
    private Product shoes;
    private TradeInRequest tradeInRequest1;
    private TradeInRequest tradeInRequest2;
    private final Product newBag = new Bag(
            PENDING,
            BAG,
            GUCCI,
            "Simple bag",
            USED,
            "No details",
            "link somewhere",
            MEDIUM,
            Set.of(BOX, MIRROR)
    );
    private final Product newShoes = new Shoes(
            PENDING,
            SHOES,
            CHANEL,
            "Simple shoes",
            GOOD,
            "No details",
            "link somewhere",
            (short) 36
    );
    private final TradeInRequestDTO newTradeInRequestDTO = new TradeInRequestDTO(
            "Jason",
            "Doe",
            "jasson.doe@email.com",
            "999888777",
            "UK",
            List.of(
                    new ProductDTO(
                            "Bag",
                            "Chanel",
                            "New Fancy Bag",
                            "Very good",
                            "A new bag in very good conditions",
                            "link",
                            new BagDTO(
                                    "Medium",
                                    Set.of("Mirror", "name tag")
                            ),
                            null
                    ),
                    new ProductDTO(
                            "Shoes",
                            "Louis Vuitton",
                            "New Fancy Shoes",
                            "Good",
                            "Used shoes in good conditions",
                            "link somewhere",
                            null,
                            new ShoesDTO(
                                    (short) 35
                            )
                    )
            )

    );


    @BeforeEach
    void setUp() throws JsonProcessingException {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

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

        tradeInRequestDTOBody = objectMapper.writeValueAsString(newTradeInRequestDTO);
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
    @Order(4)
    void testCreateTradeInRequests_validBody_returnOkResponseStatus() throws Exception {
        mockMvc.perform(
                        post(baseUrl)
                                .content(tradeInRequestDTOBody)
                                .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    @Order(4)
    void testCreateTradeInRequests_validBody_returnCreatedObject() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                        post(baseUrl)
                                .content(tradeInRequestDTOBody)
                                .contentType(APPLICATION_JSON)
                )
                .andExpectAll(
                        jsonPath("$.firstName", is(newTradeInRequestDTO.getFirstName())),
                        jsonPath("$.email", is(newTradeInRequestDTO.getEmail())),
                        jsonPath("$.products", hasSize(2))
                )
                .andReturn();

        String jsonResult = mvcResult.getResponse().getContentAsString();

        TradeInRequest createdTradeInRequest = new TradeInRequest(newTradeInRequestDTO);
        createdTradeInRequest.setId(3L);
        Product createdProduct1 = createdTradeInRequest.getProducts().get(0);
        createdProduct1.setId(3L);
        Product createdProduct2 = createdTradeInRequest.getProducts().get(1);
        createdProduct2.setId(4L);
        createdTradeInRequest.setProducts(List.of(createdProduct1, createdProduct2));

        String jsonExpected = objectMapper.writeValueAsString(
                new ResponseTradeInRequestDTO(createdTradeInRequest)
        );
        assertEquals(jsonExpected, jsonResult);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            " ",
            "firstName",
            "firstName@",
            "a.a"
    })
    @Order(4)
    void testCreateTradeInRequests_invalidEmail_returnBadRequest(String emailValues) throws Exception {
        newTradeInRequestDTO.setEmail(emailValues);
        tradeInRequestDTOBody = objectMapper.writeValueAsString(newTradeInRequestDTO);
        mockMvc.perform(
                        post(baseUrl)
                                .content(tradeInRequestDTOBody)
                                .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            " ",
            "A",
            "AAAA",
            "NONEXISTENT"
    })
    @Order(4)
    void testCreateTradeInRequests_invalidEnums_returnBadRequest(String invalidCountry) throws Exception {
        newTradeInRequestDTO.setShippingCountryISOCode(invalidCountry);
        tradeInRequestDTOBody = objectMapper.writeValueAsString(newTradeInRequestDTO);
        mockMvc.perform(
                        post(baseUrl)
                                .content(tradeInRequestDTOBody)
                                .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }

    // ------------------------------ DELETE BY ID ------------------------------
    @Test
    void deleteTradeInRequestsById() {
    }
}