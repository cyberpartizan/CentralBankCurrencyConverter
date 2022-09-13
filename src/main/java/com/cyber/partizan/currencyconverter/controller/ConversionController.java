package com.cyber.partizan.currencyconverter.controller;

import com.cyber.partizan.currencyconverter.service.CurrencyConvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/convert")
@RequiredArgsConstructor
public class ConversionController {

    private final CurrencyConvertService currencyConvertService;

    @GetMapping()
    public Float convert(@RequestParam(name = "from_currency_id") String fromCurrencyId,
                         @RequestParam(name = "to_currency_id") String toCurrencyId,
                         @RequestParam(name = "amount") float amount) {
        return currencyConvertService.convert(fromCurrencyId, toCurrencyId, amount);
    }
}
