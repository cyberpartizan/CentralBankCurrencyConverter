package com.cyber.partizan.currencyconverter.mapper;

import com.cyber.partizan.currencyconverter.dto.CurrencyRatesDTO;
import com.cyber.partizan.currencyconverter.entity.Rate;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CurrencyRatesMapper {

    public abstract ArrayList<Rate> modelList(List<CurrencyRatesDTO> currencyRateDTOList, LocalDate date);

    @AfterMapping
    public void afterDto(@MappingTarget List<Rate> rates, LocalDate date) {
        rates.forEach(rate -> rate.setDate(date));
    }
}
