package com.joaodss.tradeinwebsite.service;

import com.joaodss.tradeinwebsite.dao.TradeInRequest;
import com.joaodss.tradeinwebsite.dto.ResponseTradeInRequestDTO;
import com.joaodss.tradeinwebsite.dto.TradeInRequestDTO;
import com.joaodss.tradeinwebsite.repository.TradeInRequestRepository;
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
        TradeInRequest tradeInRequest = new TradeInRequest(tradeInRequestDTO);
        TradeInRequest savedTradeInRequest = saveToDatabase(tradeInRequest);
        exportToGoogleSheets(savedTradeInRequest);
        return getById(savedTradeInRequest.getId());
    }

    public TradeInRequest saveToDatabase(TradeInRequest tradeInRequest) {
        log.info("Saving trade in request to database");
        TradeInRequest savedTradeInRequest = tradeInRequestRepository.save(tradeInRequest);
        log.info("Trade in request saved to database with id: {}", savedTradeInRequest.getId());
        return savedTradeInRequest;
    }

    //TODO: Implement save to google sheets logic
    public void exportToGoogleSheets(TradeInRequest tradeInRequest) {
        log.info("Exporting trade in request to google sheets");
        // logic to export to google sheets
        log.info("Trade in request exported to google sheets");
    }


    // -------------------- Delete trade in requests --------------------
    public ResponseTradeInRequestDTO delete(long id) {
        log.info("Deleting trade in request with id: {}", id);
        ResponseTradeInRequestDTO tradeInRequest = getById(id);
        deleteFromDatabase(id);
        deleteFromGoogleSheets(id);
//        deleteFromGoogleDrive();
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
        // logic to export to google sheets
        log.info("Trade in request with id: {} deleted from google sheets", id);
    }

    //TODO: Implement delete from google drive logic
    public void deleteFromGoogleDrive(String filesPath) {
        log.info("Deleting photos from google drive directory: {}", filesPath);
        // logic to export to google sheets
        log.info("Photos deleted from google drive");
    }

}
