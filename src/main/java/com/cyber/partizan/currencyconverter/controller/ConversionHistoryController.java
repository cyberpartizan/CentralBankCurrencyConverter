package com.cyber.partizan.currencyconverter.controller;

import com.cyber.partizan.currencyconverter.dto.HistoryWrapper;
import com.cyber.partizan.currencyconverter.entity.ConversionHistory;
import com.cyber.partizan.currencyconverter.mapper.ConversionHistoryMapper;
import com.cyber.partizan.currencyconverter.service.ConversionHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

import static com.cyber.partizan.currencyconverter.utils.DateUtils.DOT_PATTERN;

@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
public class ConversionHistoryController {

    private final ConversionHistoryService conversionHistoryService;
    private final ConversionHistoryMapper conversionHistoryMapper;

    @GetMapping("/pair")
    public List<HistoryWrapper> getHistory(@RequestParam @DateTimeFormat(pattern = DOT_PATTERN) LocalDate date,
                                           @RequestParam("from_currency_id") @NotEmpty String fromCurrencyId,
                                           @RequestParam("to_currency_id") @NotEmpty String toCurrencyId) {
        List<ConversionHistory> history = conversionHistoryService.findHistory(date, fromCurrencyId, toCurrencyId);
        return conversionHistoryMapper.toWrapperList(history);
    }
}
