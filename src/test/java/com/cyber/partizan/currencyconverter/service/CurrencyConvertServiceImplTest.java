package com.cyber.partizan.currencyconverter.service;

import com.cyber.partizan.currencyconverter.entity.Currency;
import com.cyber.partizan.currencyconverter.entity.Rate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrencyConvertServiceImplTest {

    @Mock
    private RateService rateService;
    @Mock
    private ConversionHistoryService conversionHistoryService;

    @InjectMocks
    private CurrencyConvertServiceImpl currencyConvertService;

    @Test
    void convert() {
        //Preparation
        var fromCurrencyId = "R01200";
        var toCurrencyId = "R00000";
        var now = LocalDate.now();

        Rate fromRate = new Rate();
        Currency currency = new Currency();
        currency.setName("Гонконгских долларов");
        currency.setNumCode("986");
        currency.setCharCode("HKD");

        fromRate.setCurrency(currency);
        fromRate.setDate(now);
        fromRate.setNominal(10);
        fromRate.setValue(50);

        Rate toRate = new Rate();
        toRate.setCurrency(Currency.getRubCurrency());
        toRate.setDate(now);
        toRate.setNominal(1);
        toRate.setValue(1);

        when(rateService.getRate(fromCurrencyId, now)).thenReturn(fromRate);
        when(rateService.getRate(toCurrencyId, now)).thenReturn(toRate);

        //Action
        Float result = currencyConvertService.convert(fromCurrencyId, toCurrencyId, 1000F);

        //Assertion
        verify(rateService).getRate(fromCurrencyId, now);
        verify(rateService).getRate(toCurrencyId, now);
        verify(conversionHistoryService).save(any());
        assertThat(result).isEqualTo(5000);
    }
}