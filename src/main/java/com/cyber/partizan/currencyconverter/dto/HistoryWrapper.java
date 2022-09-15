package com.cyber.partizan.currencyconverter.dto;

import com.cyber.partizan.currencyconverter.utils.LocalDateDeserializer;
import com.cyber.partizan.currencyconverter.utils.LocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class HistoryWrapper {

    private Long id;

    @JsonProperty("source_currency")
    private CurrencyWrapper sourceCurrency;

    @JsonProperty("target_currency")
    private CurrencyWrapper targetCurrency;

    @JsonProperty("source_amount")
    private Float sourceAmount;

    @JsonProperty("target_amount")
    private Float targetAmount;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate date;
}
