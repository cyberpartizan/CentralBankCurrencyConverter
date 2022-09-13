package com.cyber.partizan.currencyconverter.controller;

import com.cyber.partizan.currencyconverter.dto.CurrencyWrapper;
import com.cyber.partizan.currencyconverter.entity.Currency;
import com.cyber.partizan.currencyconverter.mapper.CurrencyMapper;
import com.cyber.partizan.currencyconverter.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/currency")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;
    private final CurrencyMapper currencyMapper;

    @GetMapping("/all")
    public List<CurrencyWrapper> getAllCurrencies(){
        List<Currency> currencies = currencyService.getCurrencies();
        return currencyMapper.toWrapperList(currencies);
    }
}
