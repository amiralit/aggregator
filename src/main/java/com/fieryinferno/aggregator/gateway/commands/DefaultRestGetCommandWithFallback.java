package com.fieryinferno.aggregator.gateway.commands;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Created by atahmasebi on 4/20/16.
 */
public class DefaultRestGetCommandWithFallback<V> extends RestGetCommand<V> {

    private final static Logger LOGGER = LoggerFactory.getLogger(DefaultRestGetCommandWithFallback.class);

    private static final int TIMEOUT_IN_MILLISECONDS = 5000;

    private final Supplier<V> fallback;
    private final Map<String, ?> args;
    private final ParameterizedTypeReference<V> responseClass;

    public DefaultRestGetCommandWithFallback(RestTemplate restTemplate, String url, final Map<String, ?> args, final ParameterizedTypeReference<V> responseClass, final Supplier<V> fallback) {
        super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(RestGetCommand.class.getSimpleName()))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionTimeoutEnabled(true)
                        .withExecutionTimeoutInMilliseconds(TIMEOUT_IN_MILLISECONDS)), restTemplate, url);

        this.fallback = fallback;
        this.args = args;
        this.responseClass = responseClass;
    }

    @Override
    protected V run() throws Exception {
        try {
            return doGet( this.responseClass, args);
        } catch (Exception e){
            LOGGER.error(MessageFormat.format("GET failed url={0}, params={1}", url, args), e);
            throw e;
        }
    }

    @Override
    protected V getFallback(){
        return this.fallback.get();
    }
}
