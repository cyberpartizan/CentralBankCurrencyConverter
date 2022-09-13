package com.cyber.partizan.currencyconverter.service;

import com.cyber.partizan.currencyconverter.entity.ConversionHistory;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public interface ConversionHistoryService {

    ConversionHistory save(ConversionHistory historyEntity);

    List<ConversionHistory> findHistory(@NotNull LocalDate date, @NotEmpty String fromCurrencyId, @NotEmpty String toCurrencyId);
}
