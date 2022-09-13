package com.cyber.partizan.currencyconverter.service;

import com.cyber.partizan.currencyconverter.entity.ConversionHistory;
import com.cyber.partizan.currencyconverter.repository.ConversionHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConversionHistoryServiceImpl implements ConversionHistoryService {

    private final ConversionHistoryRepository conversionHistoryRepository;

    @Override
    public ConversionHistory save(ConversionHistory historyEntity) {
        return conversionHistoryRepository.save(historyEntity);
    }

    @Override
    public List<ConversionHistory> findHistory(@NotNull LocalDate date, @NotEmpty String fromCurrencyId, @NotEmpty String toCurrencyId) {
        return conversionHistoryRepository.findHistory(date, fromCurrencyId, toCurrencyId);
    }
}
