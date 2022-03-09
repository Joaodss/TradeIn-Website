package com.joaodss.tradeinwebsite;

import com.joaodss.tradeinwebsite.request.service.impl.GoogleSheetsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.security.GeneralSecurityException;

@SpringBootTest
@ActiveProfiles("test")
@PropertySource("classpath:application.properties")
class TradeInWebsiteApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void test() throws GeneralSecurityException, IOException {
        GoogleSheetsServiceImpl g = new GoogleSheetsServiceImpl();
        System.out.println(g.sheetName);
        System.out.println(g.googleSheetId);
    }

}
