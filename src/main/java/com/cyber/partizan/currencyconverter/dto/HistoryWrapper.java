package com.cyber.partizan.currencyconverter.dto;

import com.cyber.partizan.currencyconverter.utils.LocalDateDeserializer;
import com.cyber.partizan.currencyconverter.utils.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class HistoryWrapper {

    private Long id;

    private CurrencyWrapper sourceCurrency;

    private CurrencyWrapper targetCurrency;

    private Long sourceAmount;

    private Long targetAmount;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate date;
}
