package com.cyber.partizan.currencyconverter.service;

import com.cyber.partizan.currencyconverter.dto.CurrencyWrapper;

import java.util.List;

public interface CurrencyService {
    List<CurrencyWrapper> getCurrencies();
}
