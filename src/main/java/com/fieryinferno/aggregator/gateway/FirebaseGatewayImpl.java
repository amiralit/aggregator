package com.fieryinferno.aggregator.gateway;

import com.fasterxml.jackson.databind.JsonNode;
import com.fieryinferno.aggregator.gateway.commands.RestCommandFactory;

import com.firebase.security.token.TokenGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by atahmasebi on 4/24/16.
 */
@Component
public class FirebaseGatewayImpl implements FirebaseGateway {

    private static final Logger LOGGER = LoggerFactory.getLogger(FirebaseGatewayImpl.class);

    private TokenGenerator tokenGenerator = new TokenGenerator("hfj2AqzjV9M80kOHUK0FPgslMN5pevgsvSGnPN3J");

    @Autowired
    RestTemplate restTemplate;

    @Override
    public void putMatchDetails(String matchId, JsonNode matchDetails) {

        LOGGER.info("putMatchDetails={}", matchId);

        final String url = MessageFormat.format("https://fiery-inferno-5799.firebaseio.com/matches/{0}.json?auth={1}", matchId, getToken());
        restTemplate.put(url, matchDetails);
    }

    @Override
    public void putGroupStandings(String groupId, JsonNode groupStanding) {
        LOGGER.info("putGroupStandings={}", groupId);

        final String url = MessageFormat.format("https://fiery-inferno-5799.firebaseio.com/tests/Groups/{0}.json?auth={1}", groupId, getToken());
        restTemplate.put(url, groupStanding);
    }

    private String getToken(){
        Map<String, Object> authPayload = new HashMap<String, Object>();
        authPayload.put("uid", "1");

        return tokenGenerator.createToken(authPayload);
    }
}
