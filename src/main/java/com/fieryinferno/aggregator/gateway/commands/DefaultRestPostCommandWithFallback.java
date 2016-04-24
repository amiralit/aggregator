package com.fieryinferno.aggregator.gateway.commands;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.function.Supplier;

/**
 * Created by atahmasebi on 4/20/16.
 */
public class DefaultRestPostCommandWithFallback<V,K> extends RestPostCommand<V,K> {

    private final static Logger LOGGER = LoggerFactory.getLogger(DefaultRestGetCommandWithFallback.class);

    private static final int TIMEOUT_IN_MILLISECONDS = 5000;

    private final String[] args;
    private final K request;
    private final Class<V> responseClass;
    private Supplier<V> fallback;

    public DefaultRestPostCommandWithFallback(final RestTemplate restTemplate, final String url, final Object request, Class<V> responseClass, Supplier<V> fallback, String... args) {
        super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(DefaultRestPostCommandWithFallback.class.getSimpleName()))
                        .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                                .withExecutionTimeoutEnabled(true)
                                .withExecutionTimeoutInMilliseconds(TIMEOUT_IN_MILLISECONDS)),
                restTemplate,
                url);
        this.request = (K) request;
        this.args = args;
        this.responseClass = responseClass;
        this.fallback = fallback;
    }

    @Override
    protected V run() throws Exception {
        try {
            return doPost(request, this.responseClass, args);
        } catch (Exception e){
            LOGGER.error(MessageFormat.format("POST failed url={0}, params={1}", url, args), e);
            throw e;
        }
    }

    @Override
    protected V getFallback(){
        return fallback.get();
    }
}
