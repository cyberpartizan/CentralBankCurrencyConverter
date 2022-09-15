package com.cyber.partizan.currencyconverter.mapper;

import com.cyber.partizan.currencyconverter.dto.HistoryWrapper;
import com.cyber.partizan.currencyconverter.entity.ConversionHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

import static com.cyber.partizan.currencyconverter.entity.Rate.rateMultiplier;

@Mapper(componentModel = "spring", uses = {CurrencyMapper.class})
public abstract class ConversionHistoryMapper {

    @Mapping(target = "targetAmount",source = "targetAmount",qualifiedByName = "setTargetAmount")
    @Mapping(target = "sourceAmount",source = "sourceAmount", qualifiedByName = "setSourceAmount")
    public abstract HistoryWrapper toWrapper(ConversionHistory conversionHistory);

    public abstract List<HistoryWrapper> toWrapperList(List<ConversionHistory> conversionHistoryList);

    @Named("setTargetAmount")
    public Float setTargetAmount(Long targetAmount) {
        return targetAmount / rateMultiplier;
    }

    @Named("setSourceAmount")
    public Float setSourceAmount(Long sourceAmount) {
        return sourceAmount / rateMultiplier;
    }

}
