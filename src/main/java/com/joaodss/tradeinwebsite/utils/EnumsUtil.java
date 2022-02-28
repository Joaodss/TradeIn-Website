package com.joaodss.tradeinwebsite.utils;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class EnumsUtil {

    // Will format strings to UpperCase style.
    // Null inputs will return null outputs to avoid confusion with non-existing Enums.
    // Actions with null elements will throw NullPointerException exception instead of IllegalArgumentException.
    public static String enumFormat(String inputString) {
        if (inputString == null) return null;
        return inputString
                .trim()
                .replace(" ", "_")
                .replace("-", "_")
                .toUpperCase();
    }

}
