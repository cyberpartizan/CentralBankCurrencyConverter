package com.cyber.partizan.currencyconverter.service;

import com.cyber.partizan.currencyconverter.entity.ConversionHistory;

import java.time.LocalDate;
import java.util.List;

public interface ConversionHistoryService {

    ConversionHistory save(ConversionHistory historyEntity);

    List<ConversionHistory> findHistory(LocalDate fromDate,LocalDate toDate, String fromCurrencyId, String toCurrencyId);

}
