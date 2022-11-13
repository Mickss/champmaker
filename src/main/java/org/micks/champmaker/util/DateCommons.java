package org.micks.champmaker.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateCommons {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static LocalDate parse(String dateString) {
        return LocalDate.parse(dateString, FORMATTER);
    }

    public static String format(LocalDate date) {
        return null;
    }
}

