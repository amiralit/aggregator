package com.fieryinferno.aggregator.gateway.commands;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.function.Supplier;

/**
 * Created by atahmasebi on 4/23/16.
 */
public class RestCommandFactoryImpl implements RestCommandFactory{

    private static final int TIMEOUT_IN_MILLISECONDS = 5000;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public <V> RestGetCommand<V> getDefaultGetCommand(String url, Map<String, ?> args, ParameterizedTypeReference<V> responseClass) {
        return new RestGetCommand<V>(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(RestGetCommand.class.getSimpleName()))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionTimeoutEnabled(true)
                        .withExecutionTimeoutInMilliseconds(TIMEOUT_IN_MILLISECONDS)),
                restTemplate, url) {
            @Override
            protected V run() throws Exception {
                return doGet(responseClass, args);
            }
        };
    }

    @Override
    public <V, K> RestPostCommand<V, K> getDefaultPostCommand(String url, K request, Class<V> responseClass, String... args) {
        return new RestPostCommand<V,K>(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(RestGetCommand.class.getSimpleName()))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionTimeoutEnabled(true)
                        .withExecutionTimeoutInMilliseconds(TIMEOUT_IN_MILLISECONDS)),
                restTemplate, url){

            @Override
            protected V run() throws Exception {
                return doPost(request, responseClass, args);
            }
        };
    }

    @Override
    public <V, K> RestPutCommand<V, K> getDefaultPutCommand(String url, K request, Class<V> responseClass, String... args) {
        return new RestPutCommand<V,K>(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(RestGetCommand.class.getSimpleName()))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionTimeoutEnabled(true)
                        .withExecutionTimeoutInMilliseconds(TIMEOUT_IN_MILLISECONDS)),
                restTemplate, url){

            @Override
            protected V run() throws Exception {
                return doPut(request, responseClass, args);
            }
        };
    }

    @Override
    public <V, K> RestPutCommand<V, K> getDefaultPutCommandWithFallback(String url, K request, Class<V> responseClass, Supplier<V> fallback, String... args) {
        return new DefaultRestPutCommandWithFallback<V, K>(restTemplate, url, request, responseClass, fallback, args);
    }

    @Override
    public <V> RestGetCommand<V> getDefaultGetCommandWithFallback(final String url, final Map<String, ?> args, final ParameterizedTypeReference<V> responseClass, final Supplier<V> fallback){
        return new DefaultRestGetCommandWithFallback<V>(restTemplate, url, args, responseClass, fallback);
    }

    @Override
    public <V, K> RestPostCommand<V, K> getDefaultPostCommandWithFallback(String url, K request, Class<V> responseClass, final Supplier<V> fallback, String... args) {
        return new DefaultRestPostCommandWithFallback<V, K>(restTemplate, url, request, responseClass, fallback, args);
    }
}
