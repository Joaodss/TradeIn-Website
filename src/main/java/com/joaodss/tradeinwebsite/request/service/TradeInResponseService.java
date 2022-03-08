package com.joaodss.tradeinwebsite.request.service;

import com.joaodss.tradeinwebsite.request.dao.TradeInRequest;
import com.joaodss.tradeinwebsite.request.dto.ResponseProductDTO;
import com.joaodss.tradeinwebsite.request.dto.ResponseTradeInRequestDTO;
import com.joaodss.tradeinwebsite.request.dto.TradeInRequestDTO;

import java.util.List;

public interface TradeInResponseService {

    List<ResponseTradeInRequestDTO> getAll();

    List<ResponseTradeInRequestDTO> getByEmail(String email);

    ResponseTradeInRequestDTO getById(long id);

    ResponseTradeInRequestDTO create(TradeInRequestDTO tradeInRequestDTO);

    TradeInRequest saveToDatabase(TradeInRequest tradeInRequest);

    void exportToGoogleSheets(ResponseTradeInRequestDTO tradeInRequest);

    ResponseTradeInRequestDTO delete(long id);

    void deleteFromDatabase(long id);

    void deleteFromGoogleSheets(long id);

    void deleteProductsFromGoogleDrive(List<ResponseProductDTO> products);

    void deleteProductPhotos(String filesPath);

}
