package com.fieryinferno.aggregator.config;

import com.fieryinferno.aggregator.gateway.commands.RestCommandFactory;
import com.fieryinferno.aggregator.gateway.commands.RestCommandFactoryImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

/**
 * Created by atahmasebi on 4/23/16.
 */
@Configuration
public class Config {

    @Value("${LC_CLIENT_CONNECTION_TIME_OUT:2000}")
    private int connectionTimeOut;
    @Value("${LC_CLIENT_READ_TIME_OUT:5000}")
    private int readTimeOut;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate(this.httpComponentsClientHttpRequestFactory());
        ArrayList interceptorList = new ArrayList();
        restTemplate.setInterceptors(interceptorList);
        return restTemplate;
    }

    @Bean
    public HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(this.connectionTimeOut);
        clientHttpRequestFactory.setReadTimeout(this.readTimeOut);
        return clientHttpRequestFactory;
    }

    @Bean
    public RestCommandFactory restCommandFactory(){
        return new RestCommandFactoryImpl();
    }

}
