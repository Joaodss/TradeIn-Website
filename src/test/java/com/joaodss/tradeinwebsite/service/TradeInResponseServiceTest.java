package com.joaodss.tradeinwebsite.service;

import com.joaodss.tradeinwebsite.request.dao.Bag;
import com.joaodss.tradeinwebsite.request.dao.Product;
import com.joaodss.tradeinwebsite.request.dao.Shoes;
import com.joaodss.tradeinwebsite.request.dao.TradeInRequest;
import com.joaodss.tradeinwebsite.categorybrandspecification.dao.Brand;
import com.joaodss.tradeinwebsite.categorybrandspecification.dao.Category;
import com.joaodss.tradeinwebsite.request.dto.ProductDTO;
import com.joaodss.tradeinwebsite.request.dto.ResponseTradeInRequestDTO;
import com.joaodss.tradeinwebsite.request.dto.ShoesDTO;
import com.joaodss.tradeinwebsite.request.dto.TradeInRequestDTO;
import com.joaodss.tradeinwebsite.request.repository.TradeInRequestRepository;
import com.joaodss.tradeinwebsite.request.service.GoogleSheetsService;
import com.joaodss.tradeinwebsite.request.service.impl.TradeInResponseServiceImpl;
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

import static com.joaodss.tradeinwebsite.request.enums.BagSize.MEDIUM;
import static com.joaodss.tradeinwebsite.request.enums.Condition.GOOD;
import static com.joaodss.tradeinwebsite.request.enums.Condition.USED;
import static com.joaodss.tradeinwebsite.request.enums.RequestStatus.PENDING;
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
    private TradeInResponseServiceImpl tradeInResponseService;

    @Mock
    private GoogleSheetsService googleSheetsService;

    @Mock
    private TradeInRequestRepository repository;


    private final Category bag = new Category(1L, "Bag", Set.of());
    private final Category shoes = new Category(2L, "Shoes", Set.of());
    private final Brand chanel = new Brand(1L, "CHANEL", Set.of());
    private final Brand gucci = new Brand(1L, "GUCCI", Set.of());
    private Product bag1;
    private Product shoes1;
    private TradeInRequest tradeInRequest1;
    private TradeInRequest tradeInRequest2;
    private final TradeInRequestDTO tradeInRequestDTO = new TradeInRequestDTO(
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
                            "link to photos in drive",
                            null,
                            new ShoesDTO((short) 32)
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
                US
        );
        tradeInRequest1.setId(1L);
        bag1 = new Bag(
                PENDING,
                bag,
                gucci,
                "Simple bag",
                USED,
                "No details",
                "link",
                MEDIUM,
                Set.of()
        );
        bag1.setId(1L);
        tradeInRequest1.addProduct(bag1);

        tradeInRequest2 = new TradeInRequest(
                "Robin",
                "Doe",
                "robin.doe@email.com",
                "987654321",
                PT
        );
        tradeInRequest2.setId(2L);
        shoes1 = new Shoes(
                PENDING,
                shoes,
                chanel,
                "Simple shoes",
                GOOD,
                "No details",
                "link",
                (short) 36
        );
        shoes1.setId(2L);
        tradeInRequest2.addProduct(shoes1);
    }


    @Test
    @Order(1)
    void testGetAll_useTradeInRequestRepositoryFindAllJoined() {
        tradeInResponseService.getAll();

        verify(repository, times(1)).findAllJoined();
        verifyNoMoreInteractions(repository);
    }


    @Test
    @Order(2)
    void testGetByEmail_useTradeInRequestRepositoryFindByEmailJoined() {
        tradeInResponseService.getByEmail(anyString());

        verify(repository, times(1)).findByEmailJoined(anyString());
        verifyNoMoreInteractions(repository);
    }


    @Test
    @Order(3)
    void testGetById_useTradeInRequestRepositoryFindByIdJoined() {
        when(repository.findByIdJoined(anyLong()))
                .thenReturn(Optional.of(tradeInRequest1));

        tradeInResponseService.getById(anyLong());

        verify(repository, times(1)).findByIdJoined(anyLong());
        verifyNoMoreInteractions(repository);
    }

    @Test
    @Order(3)
    void testGetById_validId_returnValue() {
        when(repository.findByIdJoined(anyLong()))
                .thenReturn(Optional.of(tradeInRequest1));

        assertNotNull(tradeInResponseService.getById(anyLong()));
    }

    @Test
    @Order(3)
    void testGetById_invalidId_throwException() {
        when(repository.findByIdJoined(anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> tradeInResponseService.getById(anyLong()));
    }


    @Test
    @Order(4)
    void testCreate_useInternalMethods_saveAndExport() {
        when(repository.save(any(TradeInRequest.class)))
                .thenReturn(tradeInRequest2);
        when(repository.findByIdJoined(anyLong()))
                .thenReturn(Optional.of(tradeInRequest2));

        tradeInResponseService.create(tradeInRequestDTO);

        verify(tradeInResponseService, times(1)).saveToDatabase(any(TradeInRequest.class));
        verify(tradeInResponseService, times(1)).getById(anyLong());
        verify(tradeInResponseService, times(1))
                .exportToGoogleSheets(any(ResponseTradeInRequestDTO.class));
    }


    @Test
    @Order(5)
    void testSaveToDatabase_useRepositorySave() {
        when(repository.save(any(TradeInRequest.class)))
                .thenReturn(tradeInRequest1);

        tradeInResponseService.saveToDatabase(tradeInRequest1);

        verify(repository, times(1)).save(tradeInRequest1);
        verifyNoMoreInteractions(repository);
        verifyNoInteractions(googleSheetsService);
    }


    @Test
    @Order(6)
    void testExportToGoogleSheets_useGoogleSheetsServiceWriteTradeInRequest() {
        ResponseTradeInRequestDTO responseTradeInRequestDTO = new ResponseTradeInRequestDTO(tradeInRequest1);
        tradeInResponseService.exportToGoogleSheets(responseTradeInRequestDTO);

        verify(googleSheetsService, times(1)).writeTradeInRequest(responseTradeInRequestDTO);
        verifyNoMoreInteractions(googleSheetsService);
        verifyNoInteractions(repository);
    }


    //TODO: refactor testing and more testing
    @Test
    @Order(7)
    void testDelete_useInternalMethods_deleteFromDatabaseSheetsAndPhotos() {
        when(repository.findByIdJoined(anyLong()))
                .thenReturn(Optional.of(tradeInRequest1));

        tradeInResponseService.delete(1);

        verify(tradeInResponseService, times(1)).getById(anyLong());
        verify(tradeInResponseService, times(1)).deleteFromDatabase(anyLong());
        verify(tradeInResponseService, times(1)).deleteFromGoogleSheets(anyLong());
        verify(tradeInResponseService, times(1)).deleteProductsFromGoogleDrive(anyList());
    }


    @Test
    @Order(8)
    void testDeleteFromDatabase_useRepository_deleteById() {
        tradeInResponseService.deleteFromDatabase(1);

        verify(repository, times(1)).deleteById(1L);
        verifyNoMoreInteractions(repository);
    }


    @Disabled
    @Test
    @Order(9)
    void testDeleteFromGoogleSheets_useGoogleSheetsService_() {

    }


    @Test
    @Order(10)
    void testDeleteProductsFromGoogleDrive_useInternalMethods_deleteProductPhotos() {
        ResponseTradeInRequestDTO responseTradeInRequestDTO = new ResponseTradeInRequestDTO(tradeInRequest1);

        tradeInResponseService.deleteProductsFromGoogleDrive(responseTradeInRequestDTO.getProducts());

        verify(tradeInResponseService, times(1)).deleteProductPhotos(anyString());
    }

    @Test
    @Order(10)
    void testDeleteProductsFromGoogleDrive_useInternalMethods_multipleProducts_multipleRequests() {
        tradeInRequest1.addProduct(shoes1);
        ResponseTradeInRequestDTO responseTradeInRequestDTO = new ResponseTradeInRequestDTO(tradeInRequest1);

        tradeInResponseService.deleteProductsFromGoogleDrive(responseTradeInRequestDTO.getProducts());

        verify(tradeInResponseService, times(2)).deleteProductPhotos(anyString());
    }


    @Disabled
    @Test
    @Order(11)
    void testDeleteProductPhotos_() {
    }


}