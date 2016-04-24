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
 * Created by atahmasebi on 4/22/16.
 */
public class DefaultRestPutCommandWithFallback<V,K> extends RestPutCommand<V,K> {

    private final static Logger LOGGER = LoggerFactory.getLogger(DefaultRestGetCommandWithFallback.class);

    private static final int TIMEOUT_IN_MILLISECONDS = 5000;

    private final String[] args;
    private final K request;
    private final Class<V> responseClass;
    private Supplier<V> fallback;

    public DefaultRestPutCommandWithFallback(final RestTemplate restTemplate, final String url, final Object request, Class<V> responseClass, Supplier<V> fallback, String... args) {
        super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(DefaultRestPutCommandWithFallback.class.getSimpleName()))
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
            return doPut(request, this.responseClass, args);
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
