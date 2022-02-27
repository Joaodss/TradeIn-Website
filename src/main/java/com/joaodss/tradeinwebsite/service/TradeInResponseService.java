package com.joaodss.tradeinwebsite.service;

import com.joaodss.tradeinwebsite.dto.ResponseTradeInRequestDTO;

import java.util.List;
import java.util.Optional;

public interface TradeInResponseService {

    List<ResponseTradeInRequestDTO> getAll();

    Optional<ResponseTradeInRequestDTO> getById();

    ResponseTradeInRequestDTO create();

    ResponseTradeInRequestDTO delete();

}
