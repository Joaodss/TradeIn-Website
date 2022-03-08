package com.joaodss.tradeinwebsite.request.controller;

import com.joaodss.tradeinwebsite.request.dto.ResponseTradeInRequestDTO;
import com.joaodss.tradeinwebsite.request.dto.TradeInRequestDTO;

import java.util.List;

public interface TradeInRequestController {

    List<ResponseTradeInRequestDTO> getAllTradeInRequests();

    List<ResponseTradeInRequestDTO> getTradeInRequestsByEmail(String email);

    ResponseTradeInRequestDTO getTradeInRequestById(long id);

    ResponseTradeInRequestDTO createTradeInRequests(TradeInRequestDTO tradeInRequestDTO);

    ResponseTradeInRequestDTO deleteTradeInRequestsById(long id);

}
