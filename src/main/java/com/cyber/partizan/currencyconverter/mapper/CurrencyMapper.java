package com.cyber.partizan.currencyconverter.mapper;

import com.cyber.partizan.currencyconverter.dto.CurrencyDTO;
import com.cyber.partizan.currencyconverter.dto.CurrencyWrapper;
import com.cyber.partizan.currencyconverter.entity.Currency;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CurrencyMapper {

    public abstract Currency model(CurrencyDTO currencyDTO);
    public abstract List<Currency> modelList(List<CurrencyDTO> currencyDTOList);

    public abstract CurrencyWrapper toWrapper(Currency currency);
    public abstract List<CurrencyWrapper> toWrapperList(List<Currency> currencyList);
}
