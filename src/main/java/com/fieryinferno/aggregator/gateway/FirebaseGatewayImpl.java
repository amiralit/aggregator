package com.fieryinferno.aggregator.gateway;

import com.fasterxml.jackson.databind.JsonNode;
import com.fieryinferno.aggregator.gateway.commands.RestCommandFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;

/**
 * Created by atahmasebi on 4/24/16.
 */
@Component
public class FirebaseGatewayImpl implements FirebaseGateway {

    private static final Logger LOGGER = LoggerFactory.getLogger(FirebaseGatewayImpl.class);

    @Autowired
    RestTemplate restTemplate;

    @Override
    public void putMatchDetails(String matchId, JsonNode matchDetails) {

        LOGGER.info("putMatchDetails={}", matchId);

        final String url = MessageFormat.format("https://fiery-inferno-5799.firebaseio.com/matches/{0}.json", matchId);
        restTemplate.put(url, matchDetails);
    }
}
