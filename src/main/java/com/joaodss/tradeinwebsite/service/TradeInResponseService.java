package com.joaodss.tradeinwebsite.service;

import com.joaodss.tradeinwebsite.dao.request.TradeInRequest;
import com.joaodss.tradeinwebsite.dto.request.ResponseProductDTO;
import com.joaodss.tradeinwebsite.dto.request.ResponseTradeInRequestDTO;
import com.joaodss.tradeinwebsite.dto.request.TradeInRequestDTO;

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
