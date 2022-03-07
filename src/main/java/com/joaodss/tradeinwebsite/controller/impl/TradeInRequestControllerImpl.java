package com.joaodss.tradeinwebsite.controller.impl;

import com.joaodss.tradeinwebsite.controller.TradeInRequestController;
import com.joaodss.tradeinwebsite.dto.request.ResponseTradeInRequestDTO;
import com.joaodss.tradeinwebsite.dto.request.TradeInRequestDTO;
import com.joaodss.tradeinwebsite.service.TradeInResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.*;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/v1/trade-in-request")
@RequiredArgsConstructor
@Slf4j
public class TradeInRequestControllerImpl implements TradeInRequestController {
    private final TradeInResponseService service;


    // ------------------------------ Get all ------------------------------
    @GetMapping
    @ResponseStatus(OK)
    public List<ResponseTradeInRequestDTO> getAllTradeInRequests() {
        try {
            log.info("Getting all trade in requests");
            return service.getAll();
        } catch (Exception e) {
            log.error("Something went wrong: {}", e.getMessage());
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "Something went wrong.");
        }
    }

    // ------------------------------ Get by Email ------------------------------
    @GetMapping(params = "email")
    @ResponseStatus(OK)
    public List<ResponseTradeInRequestDTO> getTradeInRequestsByEmail(@RequestParam String email) {
        try {
            log.info("Getting trade in requests by email");
            return service.getByEmail(email);
        } catch (Exception e) {
            log.error("Something went wrong: {}", e.getMessage());
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "Something went wrong.");
        }
    }

    // ------------------------------ Get by Id ------------------------------
    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public ResponseTradeInRequestDTO getTradeInRequestById(@PathVariable("id") long id) {
        try {
            log.info("Getting trade in request by id");
            return service.getById(id);
        } catch (NoSuchElementException e1) {
            log.error("Trade in request with with id: {} not found. Error: {} ", id, e1.getMessage());
            throw new ResponseStatusException(NOT_FOUND, "Trade in request not found.");
        } catch (Exception e) {
            log.error("Something went wrong: {}", e.getMessage());
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "Something went wrong.");
        }
    }

    // ------------------------------ Create ------------------------------
    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseTradeInRequestDTO createTradeInRequests(@RequestBody @Valid TradeInRequestDTO tradeInRequestDTO) {
        try {
            log.info("Creating trade in request");
            return service.create(tradeInRequestDTO);
        } catch (NoSuchElementException e1) {
            log.error("Trade in request created not found. Error: {} ", e1.getMessage());
            throw new ResponseStatusException(NOT_FOUND, "Error fetching created trade in request.");
        } catch (IllegalArgumentException e2) {
            log.error("Invalid trade in request. Error: {} ", e2.getMessage());
            throw new ResponseStatusException(BAD_REQUEST, "Invalid trade in request.");
        } catch (Exception e) {
            log.error("Something went wrong: {}", e.getMessage());
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "Something went wrong.");
        }
    }

    // ------------------------------ Delete by Id ------------------------------
    @Override
    public ResponseTradeInRequestDTO deleteTradeInRequestsById(long id) {
        return null;
    }
}
