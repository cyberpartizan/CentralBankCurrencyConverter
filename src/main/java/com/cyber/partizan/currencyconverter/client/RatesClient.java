package com.cyber.partizan.currencyconverter.client;

import com.cyber.partizan.currencyconverter.dto.CurrencyRatesDTO;
import lombok.SneakyThrows;

import java.time.LocalDate;

public interface RatesClient {

    @SneakyThrows
    CurrencyRatesDTO getRates(LocalDate date);
}
