package com.fieryinferno.aggregator.config;

import com.fieryinferno.aggregator.events.Event;
import com.fieryinferno.aggregator.events.EventTypes;
import com.fieryinferno.aggregator.events.Observer;
import com.fieryinferno.aggregator.events.Publisher;
import com.fieryinferno.aggregator.events.PublisherImpl;
import com.fieryinferno.aggregator.gateway.commands.RestCommandFactory;
import com.fieryinferno.aggregator.gateway.commands.RestCommandFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(Config.class);

    private int connectionTimeOut = 60000;

    private int readTimeOut = 60000;

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
    public RestCommandFactory restCommandFactory() {
        return new RestCommandFactoryImpl();
    }

    @Bean
    Publisher publisher(){
        Publisher publisher = new PublisherImpl();
        publisher.addObserver(EventTypes.MATCH_STARTED, new Observer() {
            @Override
            public void notify(Event event) {
                LOGGER.info("{} - {}", EventTypes.MATCH_STARTED, event.getMatchId());
            }
        });

        publisher.addObserver(EventTypes.MATCH_ENDED, new Observer() {
            @Override
            public void notify(Event event) {
                LOGGER.info("{} - {}", EventTypes.MATCH_ENDED, event.getMatchId());
            }
        });

        return publisher;
    }
}
