package com.joaodss.tradeinwebsite.utils;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.List;

import static com.google.api.services.sheets.v4.SheetsScopes.SPREADSHEETS;
import static java.util.Collections.singletonList;
import static lombok.AccessLevel.PRIVATE;

/**
 * From: https://developers.google.com/sheets/api/quickstart/java
 */
@NoArgsConstructor(access = PRIVATE)
public class GoogleSheetsUtil {
    private static final String APPLICATION_NAME = "TradeIn Website";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = singletonList(SPREADSHEETS);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
    private static final String SERVICE_CREDENTIALS_FILE_PATH = "/service_credentials.json";

    /**
     * Creates an authorized Credential object.
     *
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static GoogleCredentials getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        GoogleCredentials googleCredentials;
        try (InputStream inputSteam = GoogleSheetsUtil.class.getResourceAsStream(SERVICE_CREDENTIALS_FILE_PATH)) {
            googleCredentials = GoogleCredentials.fromStream(inputSteam).createScoped(SCOPES);
        }
        return googleCredentials;
    }

    public static Sheets getSheetsService() throws GeneralSecurityException, IOException {
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpCredentialsAdapter(getCredentials(HTTP_TRANSPORT)))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

}


