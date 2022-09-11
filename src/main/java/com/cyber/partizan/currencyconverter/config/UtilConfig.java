package com.cyber.partizan.currencyconverter.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class UtilConfig {

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }

    @Bean
    public XmlMapper objectMapperXml() {
        return new XmlMapper();
    }

    @Bean
    @Primary
    public ObjectMapper objectMapperJson() {
        return new JsonMapper();
    }

}
