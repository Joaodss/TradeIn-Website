package com.joaodss.tradeinwebsite.utils;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class EnumsUtil {

    public static String enumFormat(String inputString) {
        return inputString
                .trim()
                .replace(" ", "_")
                .replace("-", "_")
                .toUpperCase();
    }

}
