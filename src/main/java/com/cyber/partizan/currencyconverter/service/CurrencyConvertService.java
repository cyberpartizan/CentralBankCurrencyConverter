package com.cyber.partizan.currencyconverter.service;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public interface CurrencyConvertService {
    Float convert(@NotEmpty String currencyId,@NotEmpty String toCurrencyId, @NotNull Float amount);
}
