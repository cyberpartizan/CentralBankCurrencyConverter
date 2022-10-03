package com.cyber.partizan.currencyconverter.service;

import com.cyber.partizan.currencyconverter.client.RatesClient;
import com.cyber.partizan.currencyconverter.dto.CurrencyRatesDTO;
import com.cyber.partizan.currencyconverter.dto.RateDTO;
import com.cyber.partizan.currencyconverter.entity.Currency;
import com.cyber.partizan.currencyconverter.entity.Rate;
import com.cyber.partizan.currencyconverter.mapper.CurrencyMapper;
import com.cyber.partizan.currencyconverter.repository.CurrencyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceImplTest {


    @Mock
    private CurrencyRepository currencyRepository;
    @Mock
    private RatesClient ratesClient;
    @Mock
    private CurrencyMapper currencyMapper;

    @InjectMocks
    private CurrencyServiceImpl currencyService;

    @Test
    void getCurrencies_DbHasValues() {
        //Preparation
        var c1 = Currency.getRubCurrency();
        List<Currency> currencyList = List.of(c1);
        when(currencyRepository.findAll()).thenReturn(currencyList);

        //Action
        List<Currency> currencies = currencyService.getCurrencies();

        verify(currencyRepository).findAll();
        verify(ratesClient, never()).getRates(any());
        verify(currencyMapper, never()).modelList(anyList());
        verify(currencyRepository, never()).saveAll(currencyList);
        assertThat(currencies).isEqualTo(currencyList);

    }

    @Test
    void getCurrencies_DbDontHasValues() {
        //Preparation
        var now = LocalDate.now();

        Rate r2 = new Rate();
        r2.setCurrency(Currency.getRubCurrency());
        r2.setDate(now);
        r2.setNominal(1);
        r2.setValue(1);

        RateDTO rateDTO2 = new RateDTO();
        rateDTO2.setName(Currency.getRubCurrency().getName());
        rateDTO2.setNominal(1);
        rateDTO2.setValue("1");

        List<RateDTO> rateListDto = List.of(rateDTO2);
        List<Currency> rateList = new ArrayList<>(List.of(Currency.getRubCurrency()));

        var c1 = Currency.getRubCurrency();
        List<Currency> currencyList = new ArrayList<>();
        CurrencyRatesDTO currencyRatesDTO = new CurrencyRatesDTO(c1.getName(), LocalDate.now(), rateListDto);
        when(currencyRepository.findAll()).thenReturn(currencyList);
        when(ratesClient.getRates(any())).thenReturn(currencyRatesDTO);
        when(currencyMapper.modelList(anyList())).thenReturn(rateList);

        //Action
        List<Currency> currencies = currencyService.getCurrencies();

        verify(currencyRepository).findAll();
        verify(ratesClient).getRates(now);
        verify(currencyMapper).modelList(anyList());
        verify(currencyRepository).saveAll(anyList());
        assertThat(currencies).isEqualTo(List.of(Currency.getRubCurrency(), Currency.getRubCurrency()));

    }
}