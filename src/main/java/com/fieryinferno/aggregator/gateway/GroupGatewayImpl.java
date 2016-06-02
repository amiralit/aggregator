package com.fieryinferno.aggregator.gateway;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by atahmasebi on 4/23/16.
 */
@Component
public class GroupGatewayImpl implements GroupGateway{

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupGatewayImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Optional<JsonNode> getGroupStandings(final String groupId) {

        final String url = "http://www.resultados-futbol.com/scripts/api/api.php?key=40b2f1fd2a56cbd88df8b2c9b291760f&req=tables&format=json&lang=en&league=177&year=2016&group="+groupId;

        final String response = restTemplate.getForObject(url, String.class);

        try {
            return Optional.ofNullable(new ObjectMapper().readTree(response));
        } catch (IOException e) {
            LOGGER.error("Error getting group standing for group: " + groupId, e);
        }

        return Optional.empty();
    }
}
