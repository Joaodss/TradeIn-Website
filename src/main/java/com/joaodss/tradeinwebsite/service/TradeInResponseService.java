package com.joaodss.tradeinwebsite.service;

import com.joaodss.tradeinwebsite.dto.ResponseTradeInRequestDTO;
import com.joaodss.tradeinwebsite.dto.TradeInRequestDTO;

import java.util.List;

public interface TradeInResponseService {

    List<ResponseTradeInRequestDTO> getAll();

    List<ResponseTradeInRequestDTO> getByEmail(String email);

    ResponseTradeInRequestDTO getById(long id);

    ResponseTradeInRequestDTO create(TradeInRequestDTO tradeInRequestDTO);

    ResponseTradeInRequestDTO delete(long id);

}
