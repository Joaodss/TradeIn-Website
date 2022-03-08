package com.joaodss.tradeinwebsite.request.service;

import com.google.api.services.sheets.v4.model.ValueRange;
import com.joaodss.tradeinwebsite.request.dto.ResponseTradeInRequestDTO;

import java.io.IOException;
import java.util.List;

public interface GoogleSheetsService {

    List<List<Object>> readAll() throws IOException;

    List<Object> readLine(long lineNumber) throws IOException;

    void writeTradeInRequest(ResponseTradeInRequestDTO responseTradeInRequestDTO);

    void writeLine(ValueRange valueRange) throws IOException;

}
