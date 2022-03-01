package com.joaodss.tradeinwebsite.service;

import com.google.api.services.sheets.v4.model.ValueRange;
import com.joaodss.tradeinwebsite.dto.ResponseTradeInRequestDTO;

import java.io.IOException;
import java.util.List;

public interface GoogleSheetsService {

    void readAll();

    List<Object> readLine(long lineNumber) throws IOException;

    void updateDatabase();

    void writeTradeInRequest(ResponseTradeInRequestDTO responseTradeInRequestDTO);

    void writeLine(ValueRange valueRange) throws IOException;

}
