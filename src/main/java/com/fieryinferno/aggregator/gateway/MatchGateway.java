package com.fieryinferno.aggregator.gateway;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Optional;

/**
 * Created by atahmasebi on 4/24/16.
 */
public interface MatchGateway {
    Optional<JsonNode> getMatches(int round);
    Optional<JsonNode> getMatcheDetails(String matchId);
}
