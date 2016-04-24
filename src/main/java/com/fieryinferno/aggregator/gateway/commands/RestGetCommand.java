package com.fieryinferno.aggregator.gateway.commands;

import com.netflix.hystrix.HystrixCommand;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by atahmasebi on 4/15/16.
 */
public abstract class RestGetCommand<V> extends HystrixCommand<V>{

    private RestTemplate restTemplate;
    final String url;

    protected RestGetCommand(Setter setter, final RestTemplate restTemplate, final String url) {
        super(setter);

        this.restTemplate = restTemplate;
        this.url = url;
    }

    @Override
    protected abstract V run() throws Exception;

    protected V doGet(ParameterizedTypeReference<V> responseClass, Map<String, ?> args){
        return restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                responseClass,
                args).getBody();
    }
}
