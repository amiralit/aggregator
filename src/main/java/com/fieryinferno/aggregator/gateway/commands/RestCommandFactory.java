package com.fieryinferno.aggregator.gateway.commands;

import org.springframework.core.ParameterizedTypeReference;

import java.util.Map;
import java.util.function.Supplier;

/**
 * Created by atahmasebi on 4/23/16.
 */
public interface RestCommandFactory {

    <V> RestGetCommand<V> getDefaultGetCommand(final String url, final Map<String, ?> args, final ParameterizedTypeReference<V> responseClass);

    <V,K> RestPostCommand<V,K> getDefaultPostCommand(final String url, final K request, final Class<V> responseClass, String... args);

    <V> RestGetCommand<V> getDefaultGetCommandWithFallback(final String url, final Map<String, ?> args, final ParameterizedTypeReference<V> responseClass, final Supplier<V> fallback);

    <V, K> RestPostCommand<V, K> getDefaultPostCommandWithFallback(String url, K request, Class<V> responseClass, final Supplier<V> fallback, String... args);

    <V,K> RestPutCommand<V,K> getDefaultPutCommand(final String url, final K request, final Class<V> responseClass, String... args);

    <V,K> RestPutCommand<V,K> getDefaultPutCommandWithFallback(final String url, final K request, final Class<V> responseClass, Supplier<V> fallback, String... args);
}

