package com.cyber.partizan.currencyconverter.mapper;

import com.cyber.partizan.currencyconverter.dto.RateDTO;
import com.cyber.partizan.currencyconverter.entity.Currency;
import com.cyber.partizan.currencyconverter.entity.Rate;
import com.cyber.partizan.currencyconverter.service.CurrencyService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class CurrencyRatesMapper {

    @Resource private  CurrencyService currencyService;

    @Mapping(target = "value", expression = "java(Integer.parseInt(rateDto.getValue().replace(\",\", \"\")))")
    @Mapping(target = "id", ignore = true)
    public abstract Rate model(RateDTO rateDto);

    public List<Rate> modelList(List<RateDTO> currencyRateDTOList, LocalDate date){
        Map<String, List<Currency>> currenciesMap = currencyService.getCurrencies()
                .stream()
                .collect(Collectors.groupingBy(Currency::getId));

        List<Rate> rateList = currencyRateDTOList.stream().map(rateDTO -> {
            var rate = model(rateDTO);
            rate.setDate(date);
            if (currenciesMap.containsKey(rateDTO.getId())) {
                var currency = currenciesMap.get(rateDTO.getId());
                rate.setCurrency(currency.get(0));
            }
            return rate;
        }).collect(Collectors.toList());
        return rateList;
    }
}
