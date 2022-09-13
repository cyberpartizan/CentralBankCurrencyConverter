package com.cyber.partizan.currencyconverter.dto;

import com.cyber.partizan.currencyconverter.utils.LocalDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@JacksonXmlRootElement(localName = "ValCurs")
@Data
public class CurrencyRatesDTO {
    @JacksonXmlProperty(localName = "name", isAttribute = true)
    private String name;

    @JacksonXmlProperty(localName = "Date", isAttribute = true)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate date;

    @JacksonXmlElementWrapper(localName = "ValCurs", useWrapping = false)
    @JacksonXmlProperty(localName = "Valute")
    private List<RateDTO> currencies;
}