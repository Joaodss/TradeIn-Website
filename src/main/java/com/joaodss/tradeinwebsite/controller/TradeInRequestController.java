package com.joaodss.tradeinwebsite.controller;

import com.joaodss.tradeinwebsite.dto.request.ResponseTradeInRequestDTO;
import com.joaodss.tradeinwebsite.dto.request.TradeInRequestDTO;

import java.util.List;

public interface TradeInRequestController {

    List<ResponseTradeInRequestDTO> getAllTradeInRequests();

    List<ResponseTradeInRequestDTO> getTradeInRequestsByEmail(String email);

    ResponseTradeInRequestDTO getTradeInRequestById(long id);

    ResponseTradeInRequestDTO createTradeInRequests(TradeInRequestDTO tradeInRequestDTO);

    ResponseTradeInRequestDTO deleteTradeInRequestsById(long id);

}
