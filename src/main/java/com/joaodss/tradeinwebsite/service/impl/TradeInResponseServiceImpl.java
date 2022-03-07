package com.joaodss.tradeinwebsite.service.impl;

import com.joaodss.tradeinwebsite.dao.request.TradeInRequest;
import com.joaodss.tradeinwebsite.dto.request.ResponseProductDTO;
import com.joaodss.tradeinwebsite.dto.request.ResponseTradeInRequestDTO;
import com.joaodss.tradeinwebsite.dto.request.TradeInRequestDTO;
import com.joaodss.tradeinwebsite.repository.request.TradeInRequestRepository;
import com.joaodss.tradeinwebsite.service.GoogleSheetsService;
import com.joaodss.tradeinwebsite.service.TradeInResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TradeInResponseServiceImpl implements TradeInResponseService {
    private final TradeInRequestRepository tradeInRequestRepository;
    private final GoogleSheetsService googleSheetsService;


    // -------------------- Get trade in requests --------------------
    public List<ResponseTradeInRequestDTO> getAll() {
        log.info("Getting all trade in requests");
        return tradeInRequestRepository.findAllJoined().stream()
                .map(ResponseTradeInRequestDTO::new)
                .collect(Collectors.toList());
    }

    public List<ResponseTradeInRequestDTO> getByEmail(String email) {
        log.info("Getting all trade in requests by email: {}", email);
        return tradeInRequestRepository.findByEmailJoined(email).stream()
                .map(ResponseTradeInRequestDTO::new)
                .collect(Collectors.toList());
    }

    public ResponseTradeInRequestDTO getById(long id) {
        log.info("Getting trade in request by id: {}", id);
        return tradeInRequestRepository.findByIdJoined(id)
                .map(ResponseTradeInRequestDTO::new)
                .orElseThrow(() -> new NoSuchElementException("Trade In Request with id: " + id + " not found."));
    }


    // -------------------- Create trade in requests --------------------
    public ResponseTradeInRequestDTO create(TradeInRequestDTO tradeInRequestDTO) {
        log.info("Creating a new trade in request");
        // Create tradeInRequest
        TradeInRequest tradeInRequest = new TradeInRequest(tradeInRequestDTO);
        // Save tradeInRequest
        long savedId = saveToDatabase(tradeInRequest).getId();
        // Get tradeInRequest with products joined
        ResponseTradeInRequestDTO savedTradeInRequest = getById(savedId);
        // Export to GoogleSheets
        exportToGoogleSheets(savedTradeInRequest);
        return savedTradeInRequest;
    }

    public TradeInRequest saveToDatabase(TradeInRequest tradeInRequest) {
        log.info("Saving trade in request to database");
        TradeInRequest savedTradeInRequest = tradeInRequestRepository.save(tradeInRequest);
        log.info("Trade in request saved to database with id: {}", savedTradeInRequest.getId());
        return savedTradeInRequest;
    }

    //TODO: Implement save to google sheets logic
    public void exportToGoogleSheets(ResponseTradeInRequestDTO tradeInRequest) {
        log.info("Exporting trade in request to google sheets");
        googleSheetsService.writeTradeInRequest(tradeInRequest);
        log.info("Trade in request exported to google sheets");
    }


    // -------------------- Delete trade in requests --------------------
    public ResponseTradeInRequestDTO delete(long id) {
        log.info("Deleting trade in request with id: {}", id);
        ResponseTradeInRequestDTO tradeInRequest = getById(id);
        deleteFromDatabase(id);
        deleteFromGoogleSheets(id);
        deleteProductsFromGoogleDrive(tradeInRequest.getProducts());
        log.info("Trade in request with id: {} deleted", id);
        return tradeInRequest;
    }

    public void deleteFromDatabase(long id) {
        log.info("Deleting trade in request with id: {} from database", id);
        tradeInRequestRepository.deleteById(id);
        log.info("Trade in request with id: {} deleted from database", id);
    }

    //TODO: Implement delete from google sheets logic
    public void deleteFromGoogleSheets(long id) {
        log.info("Deleting trade in request with id: {} from google sheets", id);
        // logic to delete from google sheets
        log.info("Trade in request with id: {} deleted from google sheets", id);
    }

    public void deleteProductsFromGoogleDrive(List<ResponseProductDTO> products) {
        for (ResponseProductDTO product : products) {
            deleteProductPhotos(product.getPhotosFolderURL());
        }
    }

    //TODO: Implement delete from google drive logic
    public void deleteProductPhotos(String filesPath) {
        log.info("Deleting photos from google drive directory: {}", filesPath);
        // logic to export to google sheets
        log.info("Photos deleted from google drive");
    }

}
