package com.cyber.partizan.currencyconverter.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyWrapper {
    private String id;

    private String name;

    private int nominal;

    private String value;

    private String numCode;

    private String charCode;
}
