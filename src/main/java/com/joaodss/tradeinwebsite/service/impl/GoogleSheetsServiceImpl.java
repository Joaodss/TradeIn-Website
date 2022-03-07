package com.joaodss.tradeinwebsite.service.impl;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.joaodss.tradeinwebsite.builder.ValueRangeBuilder;
import com.joaodss.tradeinwebsite.dto.request.ResponseProductDTO;
import com.joaodss.tradeinwebsite.dto.request.ResponseTradeInRequestDTO;
import com.joaodss.tradeinwebsite.service.GoogleSheetsService;
import com.joaodss.tradeinwebsite.utils.GoogleSheetsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Service
@Slf4j
public class GoogleSheetsServiceImpl implements GoogleSheetsService {
    private final String googleSheetId = "1JR36PCKmBv3GSl1EIy-DvlOfauXEAYk2raTBc88cnvY";
    private final String sheetName = "TradeInRequests";
    private final Sheets sheetsService;

    public GoogleSheetsServiceImpl() throws GeneralSecurityException, IOException {
        this.sheetsService = GoogleSheetsUtil.getSheetsService();
    }

    @Override
    public void readAll() {

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
    public void updateDatabase() {

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
