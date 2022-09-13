package com.cyber.partizan.currencyconverter.client;

import com.cyber.partizan.currencyconverter.dto.CurrencyRatesDTO;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import static com.cyber.partizan.currencyconverter.utils.DateUtils.SLASH_DATE_FORMATTER;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Service
@RequiredArgsConstructor
public class RatesClientImpl implements RatesClient {

    private final XmlMapper objectMapper;
    private final OkHttpClient client;
    public final static String cbrUrl = "https://cbr.ru/scripts/";
    private final static String DAILY_RATES_URL = cbrUrl + "XML_daily.asp";
    private final static String DATE_PARAM_NAME = "date_req";

    @Override
    public CurrencyRatesDTO getRates(LocalDate date) {

        String formattedDate = date.format(SLASH_DATE_FORMATTER);
        Map<String, String> parameters = Collections.singletonMap(DATE_PARAM_NAME, formattedDate);

        Request query = getRequest(DAILY_RATES_URL, parameters);
        Call call = client.newCall(query);

        Response response;
        try {
            response = call.execute();
            String string = Objects.requireNonNull(response.body()).string();
            return objectMapper.readValue(string, CurrencyRatesDTO.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Request getRequest(String path, Map<String, String> parameters) {
        return request(GET, null, path, parameters);
    }

    private Request request(RequestMethod method, RequestBody requestBody, String path, Map<String, String> parameters) {
        HttpUrl.Builder httpBuilder = Objects
                .requireNonNull(HttpUrl.parse(path))
                .newBuilder();

        parameters.forEach(httpBuilder::addQueryParameter);

        return new Request.Builder()
                .method(method.toString(), requestBody)
                .url(httpBuilder.build())
                .build();
    }
}
