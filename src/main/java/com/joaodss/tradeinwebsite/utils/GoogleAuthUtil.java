package com.joaodss.tradeinwebsite.utils;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.auth.oauth2.GoogleCredentials;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.google.api.services.sheets.v4.SheetsScopes.SPREADSHEETS;
import static java.util.Collections.singletonList;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class GoogleAuthUtil {

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = singletonList(SPREADSHEETS);
    private static final String SERVICE_CREDENTIALS_FILE_PATH = "/service_credentials.json";

    /**
     * Creates an authorized Credential object.
     *
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    public static GoogleCredentials getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        GoogleCredentials googleCredentials;
        try (InputStream inputSteam = GoogleSheetsUtil.class.getResourceAsStream(SERVICE_CREDENTIALS_FILE_PATH)) {
            googleCredentials = GoogleCredentials.fromStream(inputSteam).createScoped(SCOPES);
        }
        return googleCredentials;
    }

}
