package com.cyber.partizan.currencyconverter.dto;

import com.cyber.partizan.currencyconverter.utils.LocalDateDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.time.LocalDate;

@Data
public class HistoryRequest {
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("from_currency_id")
    private String fromCurrencyId;

    @JsonProperty("to_currency_id")
    private String toCurrencyId;
}
