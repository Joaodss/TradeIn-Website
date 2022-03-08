package com.joaodss.tradeinwebsite.utils;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.auth.http.HttpCredentialsAdapter;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static com.joaodss.tradeinwebsite.utils.GoogleAuthUtil.getCredentials;
import static lombok.AccessLevel.PRIVATE;

/**
 * From: https://developers.google.com/sheets/api/quickstart/java
 */
@NoArgsConstructor(access = PRIVATE)
public class GoogleSheetsUtil {

    private static final String APPLICATION_NAME = "TradeIn Website";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();


    public static Sheets getSheetsService() throws GeneralSecurityException, IOException {
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        return new Sheets
                .Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpCredentialsAdapter(getCredentials(HTTP_TRANSPORT)))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

}
