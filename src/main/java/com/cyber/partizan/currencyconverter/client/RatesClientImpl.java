package com.cyber.partizan.currencyconverter.client;

import com.cyber.partizan.currencyconverter.dto.CurrencyRatesDTO;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.cyber.partizan.currencyconverter.utils.DateUtils.SLASH_DATE_FORMATTER;

@Service
@RequiredArgsConstructor
public class RatesClientImpl implements RatesClient {

    private final XmlMapper objectMapper;
    private final HttpClient client;
    public final static String CBR_URL = "https://cbr.ru/scripts/";
    private final static String DAILY_RATES_URL = CBR_URL + "XML_daily.asp";
    private final static String DATE_PARAM_NAME = "date_req";

    @Override
    public CurrencyRatesDTO getRates(LocalDate date) {

        String formattedDate = date.format(SLASH_DATE_FORMATTER);
        Map<String, String> parameters = Collections.singletonMap(DATE_PARAM_NAME, formattedDate);

        HttpRequest request = getRequest(DAILY_RATES_URL, parameters);

        String response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
            return objectMapper.readValue(response, CurrencyRatesDTO.class);
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private HttpRequest getRequest(String path, Map<String, String> parameters) {
        return request("GET", HttpRequest.BodyPublishers.noBody(), path, parameters);
    }

    private HttpRequest request(String method, HttpRequest.BodyPublisher requestBody, String path, Map<String, String> parameters) {
        List<String> headers = new ArrayList<>(parameters.keySet().size() * 2);
        parameters.forEach((key, value) -> {
            headers.add(key);
            headers.add(value);
        });
        return HttpRequest.newBuilder()
                .method(method, requestBody)
                .headers(headers.toArray(new String[0]))
                .uri(URI.create(path))
                .build();
    }
}
