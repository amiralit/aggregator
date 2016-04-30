package com.fieryinferno.aggregator.gateway;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Created by atahmasebi on 4/24/16.
 */
public interface FirebaseGateway {
    public void putMatchDetails(final String matchId, final JsonNode matchDetails);
    public void putGroupStandings(final String groupId, final JsonNode groupStanding);
}
