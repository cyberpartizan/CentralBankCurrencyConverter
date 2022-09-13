package com.cyber.partizan.currencyconverter.service;

import com.cyber.partizan.currencyconverter.entity.Rate;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public interface RateService {

    List<Rate> getRates(@NotNull LocalDate date);

    Rate getRate(String currencyId, LocalDate date);
}
