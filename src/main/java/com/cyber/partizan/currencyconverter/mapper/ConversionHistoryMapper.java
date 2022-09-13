package com.cyber.partizan.currencyconverter.mapper;

import com.cyber.partizan.currencyconverter.dto.HistoryWrapper;
import com.cyber.partizan.currencyconverter.entity.ConversionHistory;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CurrencyMapper.class})
public abstract class ConversionHistoryMapper {

    public abstract HistoryWrapper toWrapper(ConversionHistory conversionHistory);

    public abstract List<HistoryWrapper> toWrapperList(List<ConversionHistory> conversionHistoryList);
}
