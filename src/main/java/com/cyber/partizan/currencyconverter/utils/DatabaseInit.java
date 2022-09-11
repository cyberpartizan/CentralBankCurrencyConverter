package com.cyber.partizan.currencyconverter.utils;

import com.cyber.partizan.currencyconverter.client.RatesClient;
import com.cyber.partizan.currencyconverter.dto.CurrencyRatesDTO;
import com.cyber.partizan.currencyconverter.entity.Currency;
import com.cyber.partizan.currencyconverter.mapper.CurrencyMapper;
import com.cyber.partizan.currencyconverter.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DatabaseInit {

    private final CurrencyRepository currencyRepository;
    private final CurrencyMapper currencyMapper;
    private final RatesClient ratesClient;

    @EventListener(ApplicationReadyEvent.class)
    public void insertCurrenciesInDB() {
        List<Currency> allCurrencies = currencyRepository.findAll();
        if (allCurrencies.isEmpty()) {
            CurrencyRatesDTO rates = ratesClient.getRates();
            allCurrencies = currencyMapper.modelList(rates.getCurrencies());
            currencyRepository.saveAll(allCurrencies);
        }
    }
}