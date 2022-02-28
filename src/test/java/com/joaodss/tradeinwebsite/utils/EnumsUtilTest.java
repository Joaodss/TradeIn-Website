package com.joaodss.tradeinwebsite.utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.joaodss.tradeinwebsite.utils.EnumsUtil.enumFormat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EnumsUtilTest {

    @ParameterizedTest
    @CsvSource({
            "'',''",
            "'    ',''",
            "'TEST','TEST'",
            "'bag','BAG'",
            "'separated words','SEPARATED_WORDS'",
            "'  initialSpace','INITIALSPACE'",
            "'eveRyThing-Is wrong   ','EVERYTHING_IS_WRONG'"
    })
    void testEnumFormat_unformattedInputs_enumFormattedOutputs(String input, String output) {
        assertEquals(output, enumFormat(input));
    }

}