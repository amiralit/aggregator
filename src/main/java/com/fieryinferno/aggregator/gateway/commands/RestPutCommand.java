package com.fieryinferno.aggregator.gateway.commands;

import com.netflix.hystrix.HystrixCommand;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Created by atahmasebi on 4/20/16.
 */
public abstract class RestPutCommand<V,K> extends HystrixCommand<V> {
    private RestTemplate restTemplate;
    final String url;

    protected RestPutCommand(HystrixCommand.Setter setter, final RestTemplate restTemplate, final String url) {
        super(setter);

        this.restTemplate = restTemplate;
        this.url = url;
    }

    protected V doPut(final K request, Class<V> responseClass, String... args){
        HttpEntity<K> requestEntity = new HttpEntity<K>(request);

        ResponseEntity<V> exchange = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, responseClass, args);
        return exchange.getBody();
    }
}
