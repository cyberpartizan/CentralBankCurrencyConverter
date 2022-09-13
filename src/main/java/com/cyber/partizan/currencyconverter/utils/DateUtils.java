package com.cyber.partizan.currencyconverter.utils;

import java.time.format.DateTimeFormatter;

public  class DateUtils {

    public final static DateTimeFormatter SLASH_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final String DOT_PATTERN = "dd.MM.yyyy";
    public final static DateTimeFormatter DOT_DATE_FORMATTER = DateTimeFormatter.ofPattern(DOT_PATTERN);
    }
