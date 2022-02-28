package com.joaodss.tradeinwebsite.service;

import com.joaodss.tradeinwebsite.dao.Bag;
import com.joaodss.tradeinwebsite.dao.Product;
import com.joaodss.tradeinwebsite.dao.Shoes;
import com.joaodss.tradeinwebsite.dao.TradeInRequest;
import com.joaodss.tradeinwebsite.dto.ProductDTO;
import com.joaodss.tradeinwebsite.dto.ShoesDTO;
import com.joaodss.tradeinwebsite.dto.TradeInRequestDTO;
import com.joaodss.tradeinwebsite.repository.TradeInRequestRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;
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
import static com.neovisionaries.i18n.CountryCode.PT;
import static com.neovisionaries.i18n.CountryCode.US;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class TradeInResponseServiceTest {

    @InjectMocks
    @Spy
    private TradeInResponseServiceImpl service;

    @Mock
    private TradeInRequestRepository repository;

    private Product bag;
    private Product shoes;
    private TradeInRequest tradeInRequest1;
    private TradeInRequest tradeInRequest2;
    private TradeInRequestDTO tradeInRequestDTO = new TradeInRequestDTO(
            "Robin",
            "Doe",
            "robin.doe@email.com",
            "987654321",
            "PT",
            List.of(
                    new ProductDTO(
                            "shoes",
                            "Chanel",
                            "Simple shoes",
                            "good",
                            "No details",
                            null,
                            new ShoesDTO((short) 32),
                            List.of()
                    )
            )

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
        tradeInRequest1.setId(1L);
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
        bag.setId(1L);
        tradeInRequest1.addProduct(bag);

        tradeInRequest2 = new TradeInRequest(
                "Robin",
                "Doe",
                "robin.doe@email.com",
                "987654321",
                PT,
                PENDING
        );
        tradeInRequest2.setId(2L);
        shoes = new Shoes(
                SHOES,
                CHANEL,
                "Simple shoes",
                GOOD,
                "No details",
                List.of(),
                (short) 36
        );
        shoes.setId(2L);
        tradeInRequest2.addProduct(shoes);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Order(1)
    void testGetAll_useTradeInRequestRepositoryFindAllJoined() {
        service.getAll();

        verify(repository, times(1)).findAllJoined();
        verifyNoMoreInteractions(repository);
    }

    @Test
    @Order(2)
    void testGetByEmail_useTradeInRequestRepositoryFindByEmailJoined() {
        service.getByEmail(anyString());

        verify(repository, times(1)).findByEmailJoined(anyString());
        verifyNoMoreInteractions(repository);
    }

    @Test
    @Order(3)
    void testGetById_useTradeInRequestRepositoryFindByIdJoined() {
        when(repository.findByIdJoined(anyLong()))
                .thenReturn(Optional.of(tradeInRequest1));

        service.getById(anyLong());

        verify(repository, times(1)).findByIdJoined(anyLong());
        verifyNoMoreInteractions(repository);
    }

    @Test
    @Order(3)
    void testGetById_validId_returnValue() {
        when(repository.findByIdJoined(anyLong()))
                .thenReturn(Optional.of(tradeInRequest1));

        assertNotNull(service.getById(anyLong()));
    }

    @Test
    @Order(3)
    void testGetById_invalidId_throwException() {
        when(repository.findByIdJoined(anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> service.getById(anyLong()));
    }

    //TODO: refactor testing and more testing
    @Test
    @Order(4)
    void testCreate_useTradeInRequestRepositoryCreate_useGetById() {
        when(repository.save(any(TradeInRequest.class)))
                .thenReturn(tradeInRequest2);
        when(repository.findByIdJoined(anyLong()))
                .thenReturn(Optional.of(tradeInRequest2));

        service.create(tradeInRequestDTO);

        verify(repository, times(1)).save(any(TradeInRequest.class));
        verify(service, times(1)).getById(anyLong());
        verify(repository, times(1)).findByIdJoined(anyLong());
        verifyNoMoreInteractions(repository);
    }

    //TODO: refactor testing and more testing
    @Test
    @Order(5)
    void testDelete_useTradeInRequestRepositoryDelete_useGetById() {
        when(repository.findByIdJoined(anyLong()))
                .thenReturn(Optional.of(tradeInRequest1));

        service.delete(1);

        verify(service, times(1)).getById(anyLong());
        verify(repository, times(1)).findByIdJoined(anyLong());
        verify(repository, times(1)).deleteById(anyLong());
        verifyNoMoreInteractions(repository);
    }
}