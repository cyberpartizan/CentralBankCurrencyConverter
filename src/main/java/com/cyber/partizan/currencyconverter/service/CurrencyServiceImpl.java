package com.cyber.partizan.currencyconverter.service;

import com.cyber.partizan.currencyconverter.client.RatesClient;
import com.cyber.partizan.currencyconverter.dto.CurrencyRatesDTO;
import com.cyber.partizan.currencyconverter.entity.Currency;
import com.cyber.partizan.currencyconverter.mapper.CurrencyMapper;
import com.cyber.partizan.currencyconverter.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.cyber.partizan.currencyconverter.entity.Currency.getRubCurrency;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final RatesClient ratesClient;
    private final CurrencyMapper currencyMapper;

    @Override
    public List<Currency> getCurrencies() {
        List<Currency> currencyList = currencyRepository.findAll();
        if(currencyList.isEmpty()) {
            LocalDate now = LocalDate.now();
            CurrencyRatesDTO rates = ratesClient.getRates(now);
            currencyList = currencyMapper.modelList(rates.getCurrencies());

            currencyList.add(getRubCurrency());

            currencyRepository.saveAll(currencyList);
        }
        return currencyList;
    }
}
