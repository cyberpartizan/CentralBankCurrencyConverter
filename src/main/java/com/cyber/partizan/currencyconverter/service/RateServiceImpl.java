package com.cyber.partizan.currencyconverter.service;

import com.cyber.partizan.currencyconverter.client.RatesClient;
import com.cyber.partizan.currencyconverter.dto.CurrencyRatesDTO;
import com.cyber.partizan.currencyconverter.entity.Currency;
import com.cyber.partizan.currencyconverter.entity.Rate;
import com.cyber.partizan.currencyconverter.mapper.CurrencyRatesMapper;
import com.cyber.partizan.currencyconverter.repository.RateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RateServiceImpl implements RateService {

    private final RatesClient ratesClient;
    private final CurrencyRatesMapper currencyRatesMapper;
    private final RateRepository rateRepository;


    @Override
    public List<Rate> getRates(LocalDate date){
        List<Rate> rates = rateRepository.findByDate(date);
        if(rates.isEmpty()) {
            CurrencyRatesDTO ratesDto = ratesClient.getRates(date);
            var cbCurrencies = currencyRatesMapper.modelList(ratesDto.getCurrencies(), ratesDto.getDate());
            rates = rateRepository.saveAll(cbCurrencies);
        }
        return rates;
    }

    @Override
    public Rate getRate(String currencyId, LocalDate date){
        if(Currency.getRubCurrency().getId().equals(currencyId)){
            return Rate.getRubRate(date);
        }
        return rateRepository.findByCurrencyIdAndDate(currencyId, date)
                .orElseGet(() -> {
                    List<Rate> rates = getRates(date);
                    return rates.stream()
                            .filter(rate -> rate.getCurrency().getId().equals(currencyId))
                            .findFirst()
                            .orElseThrow(EntityNotFoundException::new);
                });
    }

}
