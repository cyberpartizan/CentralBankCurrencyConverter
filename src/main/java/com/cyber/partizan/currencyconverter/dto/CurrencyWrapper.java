package com.cyber.partizan.currencyconverter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyWrapper {
    private String id;

    @JsonProperty("char_code")
    private String charCode;
}
