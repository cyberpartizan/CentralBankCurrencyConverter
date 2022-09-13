package com.cyber.partizan.currencyconverter.service;

import com.cyber.partizan.currencyconverter.entity.ConversionHistory;
import com.cyber.partizan.currencyconverter.entity.Rate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.cyber.partizan.currencyconverter.entity.Rate.rateMultiplier;

@Service
@RequiredArgsConstructor
public class CurrencyConvertServiceImpl implements CurrencyConvertService {

    private final RateService rateService;
    private final ConversionHistoryService conversionHistoryService;

    @Override
    public Float convert(String fromCurrencyId, String toCurrencyId, Float amount) {
        var now = LocalDate.now();
        Rate fromRate = rateService.getRate(fromCurrencyId, now);
        Rate toRate = rateService.getRate(toCurrencyId, now);
        Float multiplier = (((fromRate.getValue() / rateMultiplier) / fromRate.getNominal()) /
                ((toRate.getValue() / rateMultiplier) / toRate.getNominal()));
        float result = multiplier * amount;

        long sourceAmount = (long) (amount * rateMultiplier);
        long targetAmount = (long) (result * rateMultiplier);

        var historyEntity = new ConversionHistory(null, fromRate.getCurrency(), toRate.getCurrency(), sourceAmount, targetAmount, now);
        conversionHistoryService.save(historyEntity);

        return result;
    }
}
