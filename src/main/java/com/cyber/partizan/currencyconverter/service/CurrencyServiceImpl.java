package com.cyber.partizan.currencyconverter.service;

import com.cyber.partizan.currencyconverter.client.RatesClient;
import com.cyber.partizan.currencyconverter.dto.CurrencyRatesDTO;
import com.cyber.partizan.currencyconverter.entity.Currency;
import com.cyber.partizan.currencyconverter.repository.CurrencyRepository;
import com.cyber.partizan.currencyconverter.dto.CurrencyWrapper;
import com.cyber.partizan.currencyconverter.mapper.CurrencyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final RatesClient ratesClient;
    private final CurrencyMapper currencyMapper;

    @Override
    public List<CurrencyWrapper> getCurrencies(){
        List<Currency> allCurrencies = currencyRepository.findAll();
        if(allCurrencies.isEmpty()){
            CurrencyRatesDTO rates = ratesClient.getRates();
            allCurrencies = currencyMapper.modelList(rates.getCurrencies());
            currencyRepository.saveAll(allCurrencies);
        }
        return currencyMapper.toWrapperList(allCurrencies);
    }
}
