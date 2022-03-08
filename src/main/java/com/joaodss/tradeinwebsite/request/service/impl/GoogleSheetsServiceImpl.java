package com.joaodss.tradeinwebsite.request.service.impl;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.joaodss.tradeinwebsite.request.builder.ValueRangeBuilder;
import com.joaodss.tradeinwebsite.request.dto.ResponseProductDTO;
import com.joaodss.tradeinwebsite.request.dto.ResponseTradeInRequestDTO;
import com.joaodss.tradeinwebsite.request.service.GoogleSheetsService;
import com.joaodss.tradeinwebsite.utils.GoogleSheetsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class GoogleSheetsServiceImpl implements GoogleSheetsService {

    @Value("${google.sheets.url}")
    private String googleSheetId;

    @Value("${google.sheets.sheet-name}")
    private String sheetName;

    private final Sheets sheetsService;

    public GoogleSheetsServiceImpl() throws GeneralSecurityException, IOException {
        this.sheetsService = GoogleSheetsUtil.getSheetsService();
    }

    @Override
    public List<List<Object>> readAll() throws IOException {
        List<List<Object>> allResults = new ArrayList<>(new ArrayList<>());
        int i = 1;
        while (true) {
            List<Object> line = readLine(i);
            if (isEmpty(line)) break;
            allResults.add(line);
            i++;
        }
        return allResults;
    }

    private boolean isEmpty(List<Object> line) {
        for (Object cell : line)
            if (cell == null || cell.equals("")) return true;
        return false;
    }

    @Override
    public List<Object> readLine(long lineNumber) throws IOException {
        log.info("Reading line {}", lineNumber);
        // TODO: Update end range.
        String range = sheetName + "!A" + lineNumber + ":Z" + lineNumber;
        ValueRange response = sheetsService.spreadsheets().values()
                .get(googleSheetId, range)
                .setValueRenderOption("UNFORMATTED_VALUE")
                .execute();
        return response.getValues().get(0);
    }

    @Override
    public void writeTradeInRequest(ResponseTradeInRequestDTO responseTradeInRequestDTO) {
        log.info("Writing new trade in request to google sheets");
        for (ResponseProductDTO product : responseTradeInRequestDTO.getProducts()) {
            ValueRangeBuilder valueRangeBuilder = new ValueRangeBuilder();
            ValueRange valueRange = valueRangeBuilder
                    .addTimeStamp()
                    .addTradeInRequest(responseTradeInRequestDTO)
                    .addProduct(product)
                    .build();
            try {
                writeLine(valueRange);
            } catch (IOException e) {
                log.error("Error writing trade in request to google sheets", e);
                e.printStackTrace();
            }
        }
    }

    @Override
    public void writeLine(ValueRange valueRange) throws IOException {
        log.info("Writing new line");
        String range = sheetName + "!A2:T100000";
        sheetsService.spreadsheets().values()
                .append(googleSheetId, range, valueRange)
                .setValueInputOption("RAW")
                .setInsertDataOption("INSERT_ROWS")
                .execute();
    }


}
