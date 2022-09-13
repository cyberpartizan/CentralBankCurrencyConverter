package com.cyber.partizan.currencyconverter.utils;

import com.cyber.partizan.currencyconverter.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseInit {

    private final CurrencyService currencyService;

    @EventListener(ApplicationReadyEvent.class)
    public void initDB(){
        currencyService.getCurrencies();
    }
}
