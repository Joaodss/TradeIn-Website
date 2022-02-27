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
        TradeInRequest newTradeInRequest = tradeInRequestRepository.save(new TradeInRequest(tradeInRequestDTO));
        log.info("New trade in request created with id: {}", newTradeInRequest.getId());
        return getById(newTradeInRequest.getId());
    }


    // -------------------- Delete trade in requests --------------------
    public ResponseTradeInRequestDTO delete(long id) {
        log.info("Deleting trade in request with id: {}", id);
        ResponseTradeInRequestDTO tradeInRequest = getById(id);
        tradeInRequestRepository.deleteById(id);
        log.info("Trade in request with id: {} deleted", id);
        return tradeInRequest;
    }
}
